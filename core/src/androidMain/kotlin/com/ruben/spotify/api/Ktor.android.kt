package com.ruben.spotify.api

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.Logger

internal actual fun getKtorEngine(): HttpClientEngine {
    return Android.create()
}

internal actual fun getKtorLogger(): Logger {
    return Logger.ANDROID
}