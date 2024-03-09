package com.ruben.spotify.api

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.logging.Logger

internal expect fun getKtorEngine(): HttpClientEngine

internal expect fun getKtorLogger(): Logger