package com.ruben.spotify.api

import com.ruben.spotify.api.model.FeaturedPlaylists

internal class SpotifyServiceImpl(
    private val authService: AuthService,
    private val ktorService: KtorService
) : SpotifyService {
    override suspend fun getFeaturedPlaylists(): FeaturedPlaylists {
        TODO("Not yet implemented")
    }
}