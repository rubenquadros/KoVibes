package com.ruben.spotify.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.java.Java
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

internal class KtorService(engine: HttpClientEngine = Java.create()) {

    val client: HttpClient by lazy {
        HttpClient(engine) {
            defaultRequest {
               url {
                   host = "api.spotify.com"
                   protocol = URLProtocol.HTTPS
               }
            }

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }

}