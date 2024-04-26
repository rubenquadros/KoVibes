package io.github.rubenquadros.kovibes.api.album

import io.github.rubenquadros.kovibes.api.ApiResponse
import io.github.rubenquadros.kovibes.api.KtorService
import io.github.rubenquadros.kovibes.api.album.models.GetNewAlbumReleasesResponse
import io.github.rubenquadros.kovibes.api.album.models.GetAlbumTracksResponse
import io.github.rubenquadros.kovibes.api.getParsedHttpResponse
import io.github.rubenquadros.kovibes.api.models.AlbumInfo
import io.github.rubenquadros.kovibes.api.response.ErrorBody
import io.ktor.client.request.get
import io.ktor.http.path
import io.ktor.util.StringValues
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class AlbumApiImpl(
    private val ktorService: KtorService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : AlbumApi {
    override suspend fun getAlbum(id: String, market: String?): ApiResponse<AlbumInfo, ErrorBody> {
        val response = withContext(dispatcher) {
            ktorService.client.get {
                url {
                    path("v1/albums/${id}")

                    parameters.appendAll(
                        StringValues.build {
                            market?.let { this["market"] = market }
                        }
                    )
                }
            }
        }

        return response.getParsedHttpResponse()
    }

    override suspend fun getAlbumTracks(
        id: String,
        market: String?,
        limit: Int,
        offset: Int
    ): ApiResponse<GetAlbumTracksResponse, ErrorBody> {
        val response = withContext(dispatcher) {
            ktorService.client.get {
                url {
                    path("v1/albums/$id/tracks")

                    parameters.appendAll(
                        StringValues.build {
                            market?.let { this["market"] = market }
                            this["limit"] = limit.toString()
                            this["offset"] = offset.toString()
                        }
                    )
                }
            }
        }

        return response.getParsedHttpResponse()
    }

    override suspend fun getNewAlbumReleases(
        limit: Int,
        offset: Int
    ): ApiResponse<GetNewAlbumReleasesResponse, ErrorBody> {
        val response = withContext(dispatcher) {
            ktorService.client.get {
                url {
                    path("v1/browse/new-releases")

                    parameters.appendAll(
                        StringValues.build {
                            this["limit"] = limit.toString()
                            this["offset"] = offset.toString()
                        }
                    )
                }
            }
        }

        return response.getParsedHttpResponse()
    }
}