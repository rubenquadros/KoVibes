package io.github.rubenquadros.kovibes.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.http.Parameters
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

/**
 * Ktor service is responsible to provide the [client] to call the Spotify APIs.
 *
 * @property authStorage
 *
 * @param ktorEngine
 * @param ktorLogger
 */
internal class KtorService(
    ktorEngine: () -> HttpClientEngine,
    ktorLogger: () -> Logger,
    private val authStorage: AuthStorage
) {
    val client: HttpClient by lazy {
        HttpClient(ktorEngine()) {
            defaultRequest {
               url {
                   host = "api.spotify.com"
                   protocol = URLProtocol.HTTPS
               }
            }

            install(Logging) {
                logger = ktorLogger()
                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        val token = authStorage.getAccessToken()
                        if (token.isEmpty()) {
                            null
                        } else {
                            BearerTokens(token, token)
                        }
                    }

                    refreshTokens {
                        client.generateToken { httpRequestBuilder: HttpRequestBuilder ->
                            httpRequestBuilder.markAsRefreshTokenRequest()
                        }
                    }
                }
            }
        }
    }

    @OptIn(InternalAPI::class)
    private suspend fun HttpClient.generateToken(block: (httpRequestBuilder: HttpRequestBuilder) -> Unit): BearerTokens? {
        val response = runCatching {
            post {
                block(this)
                url {
                    host = "accounts.spotify.com/api/token"
                }
                body = FormDataContent(
                    Parameters.build {
                        append("grant_type", "client_credentials")
                    }
                )
                headers.apply {
                    this["Content-Type"] = "application/x-www-form-urlencoded"
                    this["Authorization"] = "Basic ${authStorage.getEncodedCredentials()}"
                }
            }
        }.getOrNull()

        return response?.let { httpResponse: HttpResponse ->
            if (httpResponse.status == HttpStatusCode.OK) {
                val token = httpResponse.body<TokenResponse>().accessToken
                authStorage.updateAccessToken(token)
                BearerTokens(token, token)
            } else {
                null
            }
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
}

