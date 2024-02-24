package com.ruben.spotify.api.test

import com.ruben.spotify.api.AuthStorage
import com.ruben.spotify.api.KtorService
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode
import io.ktor.utils.io.ByteReadChannel

object MockKtorService {
    internal fun createMockKtorService(mockConfig: Map<String, MockResponse>): KtorService {
        return KtorService(
            authStorage = AuthStorage(),
            engine = createMockEngine(mockConfig)
        )
    }

    var isSuccess: Boolean = true
}

private fun createMockEngine(mockConfig: Map<String, MockResponse>): MockEngine = MockEngine {
    val url = it.url.toString()

    for (config in mockConfig.keys) {
        if (url.contains(config)) {
            val response = mockConfig[config]

            assert(response != null) {
                "There was no response for the path: $config"
            }

            val (status, body) = if (MockKtorService.isSuccess) {
                HttpStatusCode.OK to getExpectedResponse(response!!.expectedSuccessResponsePath)
            } else {
                HttpStatusCode.BadRequest to getExpectedResponse(response!!.expectedErrorResponsePath)
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