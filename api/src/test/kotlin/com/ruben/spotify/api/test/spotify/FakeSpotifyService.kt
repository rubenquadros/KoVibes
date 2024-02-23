package com.ruben.spotify.api.test.spotify

import com.ruben.spotify.api.SpotifyService
import com.ruben.spotify.api.response.Categories
import com.ruben.spotify.api.response.ErrorBody
import com.ruben.spotify.api.response.FeaturedPlaylists
import com.ruben.spotify.api.response.Genres
import com.ruben.spotify.api.response.PlaylistTracks
import com.ruben.spotify.api.response.SpotifyApiResponse
import com.ruben.spotify.api.test.errorResponsePath
import kotlinx.serialization.json.Json

class FakeSpotifyService: SpotifyService {

    override suspend fun getFeaturedPlaylists(
        locale: String,
        limit: Int,
        offset: Int
    ): SpotifyApiResponse<FeaturedPlaylists, ErrorBody> {
        return SpotifyApiResponse.Error(
            Json.decodeFromString(errorResponsePath)
        )
    }

    override suspend fun getPlaylistTracks(
        id: String,
        market: String?,
        fields: String?,
        limit: Int,
        offset: Int
    ): SpotifyApiResponse<PlaylistTracks, ErrorBody> {
        return SpotifyApiResponse.Error(
            Json.decodeFromString(errorResponsePath)
        )
    }

    override suspend fun getGenres(): SpotifyApiResponse<Genres, ErrorBody> {
        return SpotifyApiResponse.Error(
            Json.decodeFromString(errorResponsePath)
        )
    }

    override suspend fun getCategories(
        locale: String,
        limit: Int,
        offset: Int
    ): SpotifyApiResponse<Categories, ErrorBody> {
        return SpotifyApiResponse.Error(
            Json.decodeFromString(errorResponsePath)
        )
    }
}