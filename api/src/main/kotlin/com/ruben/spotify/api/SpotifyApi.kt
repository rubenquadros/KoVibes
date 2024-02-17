package com.ruben.spotify.api

object SpotifyApi {

    private val ktorService: KtorService by lazy { KtorService() }

    private val authService: AuthService by lazy { AuthServiceImpl(ktorService) }

    private val spotifyService: SpotifyService by lazy { SpotifyServiceImpl(authService, ktorService) }

    fun createSpotifyApi(clientId: String, clientSecret: String): SpotifyService {
        authService.init(clientId, clientSecret)
        return spotifyService
    }
}