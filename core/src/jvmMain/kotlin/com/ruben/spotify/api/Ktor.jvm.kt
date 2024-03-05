package com.ruben.spotify.api

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.java.Java
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.SIMPLE

internal actual fun getKtorEngine(): HttpClientEngine {
    return Java.create()
}

internal actual fun getKtorLogger(): Logger {
    return Logger.SIMPLE
}