package com.ruben.spotify.api

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android

internal actual fun getKtorEngine(): HttpClientEngine {
    return Android.create()
}