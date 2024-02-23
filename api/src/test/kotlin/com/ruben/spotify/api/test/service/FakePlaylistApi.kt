package com.ruben.spotify.api.test.service

import com.ruben.spotify.api.ApiResponse
import com.ruben.spotify.api.playlist.PlaylistApi
import com.ruben.spotify.api.playlist.models.FeaturedPlaylistsResponse
import com.ruben.spotify.api.playlist.models.PlaylistTracksResponse
import com.ruben.spotify.api.response.ErrorBody
import com.ruben.spotify.api.test.getApiResponse
import com.ruben.spotify.api.test.getExpectedResponse
import kotlinx.serialization.json.Json

internal class FakePlaylistApi : PlaylistApi {

    companion object {
        var isSuccess: Boolean = true
    }

    override suspend fun getFeaturedPlaylists(
        locale: String,
        limit: Int,
        offset: Int
    ): ApiResponse<FeaturedPlaylistsResponse, ErrorBody> {
        return getApiResponse(isSuccess) {
            Json.decodeFromString(getExpectedResponse("playlist/featured_playlists.json"))
        }
    }

    override suspend fun getPlaylistTracks(
        id: String,
        market: String?,
        fields: String?,
        limit: Int,
        offset: Int
    ): ApiResponse<PlaylistTracksResponse, ErrorBody> {
        return getApiResponse(isSuccess) {
            Json.decodeFromString(getExpectedResponse("playlist/playlist_tracks.json"))
        }
    }
}