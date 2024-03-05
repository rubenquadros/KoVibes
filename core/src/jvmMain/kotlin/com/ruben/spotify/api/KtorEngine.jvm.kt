package com.ruben.spotify.api

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.java.Java

internal actual fun getKtorEngine(): HttpClientEngine {
    return Java.create()
}