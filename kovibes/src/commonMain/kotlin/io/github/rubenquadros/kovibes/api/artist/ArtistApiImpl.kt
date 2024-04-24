package io.github.rubenquadros.kovibes.api.artist

import io.github.rubenquadros.kovibes.api.ApiResponse
import io.github.rubenquadros.kovibes.api.KtorService
import io.github.rubenquadros.kovibes.api.artist.models.GetArtistTopTracksResponse
import io.github.rubenquadros.kovibes.api.getParsedHttpResponse
import io.github.rubenquadros.kovibes.api.models.AlbumResponse
import io.github.rubenquadros.kovibes.api.models.ArtistInfo
import io.github.rubenquadros.kovibes.api.response.ErrorBody
import io.ktor.client.request.get
import io.ktor.http.path
import io.ktor.util.StringValues
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @suppress
 * ArtistApiImpl is the implementation of [ArtistApi].
 *
 * @property ktorService
 * @property dispatcher
 */
internal class ArtistApiImpl(
    private val ktorService: KtorService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ArtistApi {
    override suspend fun getArtist(id: String): ApiResponse<ArtistInfo, ErrorBody> {
        val response = withContext(dispatcher) {
            ktorService.client.get {
                url {
                    path("v1/artists/$id")
                }
            }
        }

        return response.getParsedHttpResponse()
    }

    override suspend fun getArtistAlbums(
        id: String,
        includeGroups: List<String>,
        market: String?,
        limit: Int,
        offset: Int
    ): ApiResponse<AlbumResponse, ErrorBody> {
        val response = withContext(dispatcher) {
            ktorService.client.get {
                url {
                    path("v1/artists/$id/albums")

                    parameters.appendAll(
                        StringValues.build {
                            market?.let { this["market"] = market }
                            this["include_groups"] = includeGroups.joinToString { it }
                            this["limit"] = limit.toString()
                            this["offset"] = offset.toString()
                        }
                    )
                }
            }
        }

        return response.getParsedHttpResponse()
    }

    override suspend fun getArtistTopTracks(
        id: String,
        market: String?
    ): ApiResponse<GetArtistTopTracksResponse, ErrorBody> {
        val response = withContext(dispatcher) {
            ktorService.client.get {
                url {
                    path("v1/artists/$id/top-tracks")

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
}