package com.ruben.spotify.api.playlist

import com.ruben.spotify.api.ApiResponse
import com.ruben.spotify.api.playlist.models.FeaturedPlaylistsResponse
import com.ruben.spotify.api.playlist.models.PlaylistTracksResponse
import com.ruben.spotify.api.response.ErrorBody

internal interface PlaylistApi {

    suspend fun getFeaturedPlaylists(
        locale: String,
        limit: Int,
        offset: Int
    ): ApiResponse<FeaturedPlaylistsResponse, ErrorBody>

    suspend fun getPlaylistTracks(
        id: String,
        market: String?,
        fields: String?,
        limit: Int,
        offset: Int
    ): ApiResponse<PlaylistTracksResponse, ErrorBody>

}