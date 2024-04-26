package io.github.rubenquadros.kovibes.api.test.service

import io.github.rubenquadros.kovibes.api.ApiResponse
import io.github.rubenquadros.kovibes.api.album.AlbumApi
import io.github.rubenquadros.kovibes.api.album.models.GetAlbumTracksResponse
import io.github.rubenquadros.kovibes.api.album.models.GetNewAlbumReleasesResponse
import io.github.rubenquadros.kovibes.api.models.AlbumInfo
import io.github.rubenquadros.kovibes.api.response.ErrorBody
import io.github.rubenquadros.kovibes.api.test.getApiResponse
import io.github.rubenquadros.kovibes.api.test.getExpectedResponse
import io.github.rubenquadros.kovibes.api.test.json

internal class FakeAlbumApi : AlbumApi {

    companion object {
        var isSuccess: Boolean = true
    }

    override suspend fun getAlbum(id: String, market: String?): ApiResponse<AlbumInfo, ErrorBody> {
        return getApiResponse(isSuccess) {
            json.decodeFromString(getExpectedResponse("album/album.json"))
        }
    }

    override suspend fun getAlbumTracks(
        id: String,
        market: String?,
        limit: Int,
        offset: Int
    ): ApiResponse<GetAlbumTracksResponse, ErrorBody> {
        return getApiResponse(isSuccess) {
            json.decodeFromString(getExpectedResponse("album/tracks.json"))
        }
    }

    override suspend fun getNewAlbumReleases(
        limit: Int,
        offset: Int
    ): ApiResponse<GetNewAlbumReleasesResponse, ErrorBody> {
        return getApiResponse(isSuccess) {
            json.decodeFromString(getExpectedResponse("album/new_releases.json"))
        }
    }
}