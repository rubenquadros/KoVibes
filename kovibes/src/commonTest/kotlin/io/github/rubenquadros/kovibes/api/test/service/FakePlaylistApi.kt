package io.github.rubenquadros.kovibes.api.test.service

import io.github.rubenquadros.kovibes.api.ApiResponse
import io.github.rubenquadros.kovibes.api.playlist.PlaylistApi
import io.github.rubenquadros.kovibes.api.playlist.models.FeaturedPlaylistsResponse
import io.github.rubenquadros.kovibes.api.playlist.models.PlaylistTracksResponse
import io.github.rubenquadros.kovibes.api.response.ErrorBody
import io.github.rubenquadros.kovibes.api.test.getApiResponse
import io.github.rubenquadros.kovibes.api.test.getExpectedResponse
import io.github.rubenquadros.kovibes.api.test.json

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
            json.decodeFromString(getExpectedResponse("playlist/featured_playlists.json"))
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
            json.decodeFromString(getExpectedResponse("playlist/playlist_tracks.json"))
        }
    }
}