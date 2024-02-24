package com.ruben.spotify.api.search

import com.ruben.spotify.api.ApiResponse
import com.ruben.spotify.api.response.ErrorBody
import com.ruben.spotify.api.search.models.SearchAlbumResponse
import com.ruben.spotify.api.search.models.SearchArtistResponse
import com.ruben.spotify.api.search.models.SearchPlaylistResponse
import com.ruben.spotify.api.search.models.SearchTrackResponse

internal interface SearchApi {

    suspend fun searchTrack(
        query: String,
        market: String?,
        limit: Int,
        offset: Int
    ): ApiResponse<SearchTrackResponse, ErrorBody>

    suspend fun searchAlbum(
        query: String,
        market: String?,
        limit: Int,
        offset: Int
    ): ApiResponse<SearchAlbumResponse, ErrorBody>

    suspend fun searchArtist(
        query: String,
        market: String?,
        limit: Int,
        offset: Int
    ): ApiResponse<SearchArtistResponse, ErrorBody>

    suspend fun searchPlaylist(
        query: String,
        market: String?,
        limit: Int,
        offset: Int
    ): ApiResponse<SearchPlaylistResponse, ErrorBody>
}