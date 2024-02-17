package com.ruben.spotify.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.client.request.post
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.http.Parameters
import io.ktor.util.InternalAPI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

internal class AuthServiceImpl(
    private val ktorService: KtorService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : AuthService {

    private var clientId: String = ""
    private var clientSecret: String = ""

    private var accessToken: String = ""

    override fun init(clientId: String, clientSecret: String) {
        this.clientId = clientId
        this.clientSecret = clientSecret
    }

    @OptIn(InternalAPI::class)
    override suspend fun generateToken(): AuthResult {
        val client = getKtorClient()
        val httpResponse: HttpResponse = withContext(dispatcher) {
            client.post {
                body = FormDataContent(
                    Parameters.build {
                        append("grant_type", "client_credentials")
                    }
                )
            }
        }

        return if (httpResponse.status == HttpStatusCode.OK) {
            val body = httpResponse.body<TokenResponse>()
            updateToken(accessToken = body.accessToken)
            AuthResult.Success
        } else {
            AuthResult.Error
        }
    }

    override fun getAccessToken(): String {
        return accessToken
    }

    @OptIn(ExperimentalEncodingApi::class)
    private fun generateBearer(): String {
        return Base64.encode(
            source = "$clientId:$clientSecret".toByteArray()
        )
    }

    private fun getKtorClient(): HttpClient {
        return ktorService.client.apply {
            this.requestPipeline.intercept(HttpRequestPipeline.Before) {
                context.headers.clear()
                context.headers.apply {
                    this["Content-Type"] = "application/x-www-form-urlencoded"
                    this["Authorization"] = "Basic ${generateBearer()}"
                }
                context.url.host = "accounts.spotify.com/api/token"
            }
        }
    }

    private fun updateToken(accessToken: String) {
        println("AccessToken: $accessToken")
        this.accessToken = accessToken
    }
}

@Serializable
internal data class TokenResponse(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("token_type")
    val tokenType: String,
    @SerialName("expires_in")
    val expiresIn: Long
)

internal sealed interface AuthResult {
    data object Success: AuthResult
    data object Error: AuthResult
}