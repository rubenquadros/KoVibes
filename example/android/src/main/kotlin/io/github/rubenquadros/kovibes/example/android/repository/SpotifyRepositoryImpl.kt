package io.github.rubenquadros.kovibes.example.android.repository

import io.github.rubenquadros.kovibes.api.SpotifyApi
import io.github.rubenquadros.kovibes.api.SpotifyService
import io.github.rubenquadros.kovibes.api.response.Categories
import io.github.rubenquadros.kovibes.api.response.Genres
import io.github.rubenquadros.kovibes.api.response.Playlists
import io.github.rubenquadros.kovibes.api.response.SpotifyApiResponse
import io.github.rubenquadros.kovibes.example.android.BuildConfig
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

private fun <RESPONSE, ERROR> SpotifyApiResponse<RESPONSE, ERROR>.parseResponse(): RESPONSE? {
    return if (this is SpotifyApiResponse.Success) {
        this.result
    } else {
        null
    }
}