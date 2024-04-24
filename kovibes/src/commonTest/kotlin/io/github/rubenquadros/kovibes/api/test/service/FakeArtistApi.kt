package io.github.rubenquadros.kovibes.api.test.service

import io.github.rubenquadros.kovibes.api.ApiResponse
import io.github.rubenquadros.kovibes.api.artist.ArtistApi
import io.github.rubenquadros.kovibes.api.artist.models.GetArtistTopTracksResponse
import io.github.rubenquadros.kovibes.api.models.AlbumResponse
import io.github.rubenquadros.kovibes.api.models.ArtistInfo
import io.github.rubenquadros.kovibes.api.response.ErrorBody
import io.github.rubenquadros.kovibes.api.test.getApiResponse
import io.github.rubenquadros.kovibes.api.test.getExpectedResponse
import kotlinx.serialization.json.Json

internal class FakeArtistApi : ArtistApi {

    companion object {
        var isSuccess: Boolean = true
    }
    override suspend fun getArtist(id: String): ApiResponse<ArtistInfo, ErrorBody> {
        return getApiResponse(isSuccess) {
            Json.decodeFromString(getExpectedResponse("artist/artist.json"))
        }
    }

    override suspend fun getArtistAlbums(
        id: String,
        includeGroups: List<String>,
        market: String?,
        limit: Int,
        offset: Int
    ): ApiResponse<AlbumResponse, ErrorBody> {
        return getApiResponse(isSuccess) {
            Json.decodeFromString(getExpectedResponse("artist/albums.json"))
        }
    }

    override suspend fun getArtistTopTracks(
        id: String,
        market: String?
    ): ApiResponse<GetArtistTopTracksResponse, ErrorBody> {
        return getApiResponse(isSuccess) {
            Json.decodeFromString(getExpectedResponse("artist/top_tracks.json"))
        }
    }
}