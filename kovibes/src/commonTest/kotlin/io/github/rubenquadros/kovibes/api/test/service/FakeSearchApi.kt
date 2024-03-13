package io.github.rubenquadros.kovibes.api.test.service

import io.github.rubenquadros.kovibes.api.ApiResponse
import io.github.rubenquadros.kovibes.api.response.ErrorBody
import io.github.rubenquadros.kovibes.api.search.SearchApi
import io.github.rubenquadros.kovibes.api.search.models.SearchAlbumResponse
import io.github.rubenquadros.kovibes.api.search.models.SearchArtistResponse
import io.github.rubenquadros.kovibes.api.search.models.SearchPlaylistResponse
import io.github.rubenquadros.kovibes.api.search.models.SearchTrackResponse
import io.github.rubenquadros.kovibes.api.test.getApiResponse
import io.github.rubenquadros.kovibes.api.test.getExpectedResponse
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