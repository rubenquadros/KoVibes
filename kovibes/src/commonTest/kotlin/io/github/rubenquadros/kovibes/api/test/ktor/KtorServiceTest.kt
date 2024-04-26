package io.github.rubenquadros.kovibes.api.test.ktor

import io.github.rubenquadros.kovibes.api.AuthStorage
import io.github.rubenquadros.kovibes.api.KtorService
import io.github.rubenquadros.kovibes.api.config.logger.LogLevel
import io.github.rubenquadros.kovibes.api.test.MockKtorService
import io.github.rubenquadros.kovibes.api.test.MockResponse
import io.github.rubenquadros.kovibes.api.test.errorResponsePath
import io.github.rubenquadros.kovibes.api.test.getExpectedResponse
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode
import io.ktor.http.path
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

class KtorServiceTest {

    private var shouldThrowException = false

    private var shouldThrowAuthError = false

    private val authStorage = AuthStorage()

    private val ktorService = KtorService(
        authStorage = authStorage,
        logLevel = { LogLevel.NONE },
        ktorEngine = { getKtorEngine() },
        ktorLogger = { Logger.SIMPLE }
    )

    @Test
    fun `when authorization fails the auth token is refreshed`() = runTest {
        MockKtorService.isSuccess = false
        ktorService.client.get {
            url {
                path("browse/categories")
            }
        }

        assertTrue { authStorage.getAccessToken() == "token123" }
    }

    @Test
    fun `when token refresh fails then the auth token is not refreshed`() = runTest {
        MockKtorService.isSuccess = false
        shouldThrowAuthError = false
        shouldThrowException = true

        ktorService.client.get {
            url {
                path("browse/categories")
            }
        }

        assertTrue { authStorage.getAccessToken().isEmpty() }
    }

    @Test
    fun `when token refresh fails then auth token is not refreshed`() = runTest {
        MockKtorService.isSuccess = false
        shouldThrowException = false
        shouldThrowAuthError = true

        ktorService.client.get {
            url {
                path("browse/categories")
            }
        }

        assertTrue { authStorage.getAccessToken().isEmpty() }
    }

    @Test
    fun `when a valid auth token is present then it is not refreshed`() = runTest {
        authStorage.updateAccessToken("token456")

        MockKtorService.isSuccess = true
        shouldThrowAuthError = false
        shouldThrowException = false

        ktorService.client.get {
            url {
                path("browse/categories")
            }
        }

        assertTrue { authStorage.getAccessToken() == "token456" }
    }

    private fun getKtorEngine(): HttpClientEngine = MockEngine {
        val url = it.url.toString()
        val mockConfig = mapOf(
            "browse/categories" to MockResponse(
                expectedSuccessResponsePath = "browse/categories.json",
                expectedErrorResponsePath = errorResponsePath
            ),
            "api/token" to MockResponse(
                expectedSuccessResponsePath = "auth.json",
                expectedErrorResponsePath = errorResponsePath
            )
        )

        for (config in mockConfig.keys) {
            if (url.contains(config)) {
                val response = mockConfig[config]

                assert(response != null) {
                    "There was no response for the path: $config"
                }

                val (status, body) = when {
                    url.contains("browse") && !MockKtorService.isSuccess -> {
                        MockKtorService.isSuccess = true
                        HttpStatusCode.Unauthorized to getExpectedResponse(response!!.expectedErrorResponsePath)
                    }
                    url.contains("api/token") && shouldThrowException -> {
                        throw Exception("Error when fetching token from server.")
                    }
                    url.contains("api/token") && shouldThrowAuthError -> {
                        HttpStatusCode.InternalServerError to getExpectedResponse(response!!.expectedSuccessResponsePath)
                    }
                    else -> {
                        HttpStatusCode.OK to getExpectedResponse(response!!.expectedSuccessResponsePath)
                    }
                }

                return@MockEngine respond(
                    content = ByteReadChannel(body),
                    status = status,
                    headers = Headers.build { this["content-type"] = "application/json" }
                )
            }
        }

        throw Exception("Request url: $url does not match the config. Please check the provided config.")
    }
}