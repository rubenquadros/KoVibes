package com.ruben.spotify.api

import com.ruben.spotify.api.playlist.PlaylistApiImpl

object SpotifyApi {

    private val authStorage: AuthStorage by lazy { AuthStorage() }

    private val ktorService: KtorService by lazy { KtorService(authStorage = authStorage) }

    private val spotifyService: SpotifyService by lazy {
        SpotifyServiceImpl(
            playlistApi = PlaylistApiImpl(ktorService)
        )
    }

    fun createSpotifyApi(clientId: String, clientSecret: String): SpotifyService {
        authStorage.init(clientId, clientSecret)
        return spotifyService
    }
}