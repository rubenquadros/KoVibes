package io.github.rubenquadros.kovibes.api.test.service

import io.github.rubenquadros.kovibes.api.ApiResponse
import io.github.rubenquadros.kovibes.api.artist.ArtistApi
import io.github.rubenquadros.kovibes.api.artist.models.GetArtistTopTracksResponse
import io.github.rubenquadros.kovibes.api.artist.models.GetRelatedArtistsResponse
import io.github.rubenquadros.kovibes.api.models.AlbumResponse
import io.github.rubenquadros.kovibes.api.models.ArtistInfo
import io.github.rubenquadros.kovibes.api.response.ErrorBody
import io.github.rubenquadros.kovibes.api.test.getApiResponse
import io.github.rubenquadros.kovibes.api.test.getExpectedResponse
import io.github.rubenquadros.kovibes.api.test.json

internal class FakeArtistApi : ArtistApi {

    companion object {
        var isSuccess: Boolean = true
    }

    override suspend fun getArtist(id: String): ApiResponse<ArtistInfo, ErrorBody> {
        return getApiResponse(isSuccess) {
            json.decodeFromString(getExpectedResponse("artist/artist.json"))
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
            json.decodeFromString(getExpectedResponse("artist/albums.json"))
        }
    }

    override suspend fun getArtistTopTracks(
        id: String,
        market: String?
    ): ApiResponse<GetArtistTopTracksResponse, ErrorBody> {
        return getApiResponse(isSuccess) {
            json.decodeFromString(getExpectedResponse("artist/top_tracks.json"))
        }
    }

    override suspend fun getRelatedArtists(id: String): ApiResponse<GetRelatedArtistsResponse, ErrorBody> {
        return getApiResponse(isSuccess) {
            json.decodeFromString(getExpectedResponse("artist/related_artists.json"))
        }
    }
}