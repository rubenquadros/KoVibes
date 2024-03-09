package com.ruben.spotify.api.test.service

import com.ruben.spotify.api.ApiResponse
import com.ruben.spotify.api.response.ErrorBody
import com.ruben.spotify.api.search.SearchApi
import com.ruben.spotify.api.search.models.SearchAlbumResponse
import com.ruben.spotify.api.search.models.SearchArtistResponse
import com.ruben.spotify.api.search.models.SearchPlaylistResponse
import com.ruben.spotify.api.search.models.SearchTrackResponse
import com.ruben.spotify.api.test.getApiResponse
import com.ruben.spotify.api.test.getExpectedResponse
import kotlinx.serialization.json.Json

internal class FakeSearchApi: SearchApi {

    companion object {
        var isSuccess: Boolean = true
    }

    override suspend fun searchTrack(
        query: String,
        market: String?,
        limit: Int,
        offset: Int
    ): ApiResponse<SearchTrackResponse, ErrorBody> {
        return getApiResponse(isSuccess) {
            Json.decodeFromString(getExpectedResponse("search/tracks.json"))
        }
    }

    override suspend fun searchAlbum(
        query: String,
        market: String?,
        limit: Int,
        offset: Int
    ): ApiResponse<SearchAlbumResponse, ErrorBody> {
        return getApiResponse(isSuccess) {
            Json.decodeFromString(getExpectedResponse("search/albums.json"))
        }
    }

    override suspend fun searchArtist(
        query: String,
        market: String?,
        limit: Int,
        offset: Int
    ): ApiResponse<SearchArtistResponse, ErrorBody> {
        return getApiResponse(isSuccess) {
            Json.decodeFromString(getExpectedResponse("search/artists.json"))
        }
    }

    override suspend fun searchPlaylist(
        query: String,
        market: String?,
        limit: Int,
        offset: Int
    ): ApiResponse<SearchPlaylistResponse, ErrorBody> {
        return getApiResponse(isSuccess) {
            Json.decodeFromString(getExpectedResponse("search/playlists.json"))
        }
    }
}