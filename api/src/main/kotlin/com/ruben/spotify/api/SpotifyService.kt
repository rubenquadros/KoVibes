package com.ruben.spotify.api

import com.ruben.spotify.api.model.FeaturedPlaylists

interface SpotifyService {
    suspend fun getFeaturedPlaylists(): FeaturedPlaylists
}