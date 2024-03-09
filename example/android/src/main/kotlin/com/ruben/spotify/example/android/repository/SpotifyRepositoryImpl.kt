package com.ruben.spotify.example.android.repository

import com.ruben.spotify.api.SpotifyApi
import com.ruben.spotify.api.SpotifyService
import com.ruben.spotify.api.response.Categories
import com.ruben.spotify.api.response.Genres
import com.ruben.spotify.api.response.Playlists
import com.ruben.spotify.api.response.SpotifyApiResponse
import com.ruben.spotify.example.android.BuildConfig
import org.koin.core.annotation.Single

@Single
class SpotifyRepositoryImpl : SpotifyRepository {

    private val spotifyService: SpotifyService = SpotifyApi.createSpotifyApi(
        clientId = BuildConfig.CLIENT_ID,
        clientSecret = BuildConfig.CLIENT_SECRET
    )

    override suspend fun getFeaturedPlaylists(): Playlists? {
        return spotifyService.getFeaturedPlaylists().parseResponse()
    }

    override suspend fun getCategories(): Categories? {
        return spotifyService.getCategories().parseResponse()
    }

    override suspend fun getGenres(): Genres? {
        return spotifyService.getGenres().parseResponse()
    }
}

private fun <RESPONSE, ERROR>SpotifyApiResponse<RESPONSE, ERROR>.parseResponse(): RESPONSE? {
    return if (this is SpotifyApiResponse.Success) {
        this.result
    } else {
        null
    }
}