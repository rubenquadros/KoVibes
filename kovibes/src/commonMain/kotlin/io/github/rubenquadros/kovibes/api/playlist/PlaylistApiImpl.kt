package io.github.rubenquadros.kovibes.api.playlist

import io.github.rubenquadros.kovibes.api.ApiResponse
import io.github.rubenquadros.kovibes.api.KtorService
import io.github.rubenquadros.kovibes.api.getParsedHttpResponse
import io.github.rubenquadros.kovibes.api.models.PlaylistInfo
import io.github.rubenquadros.kovibes.api.playlist.models.FeaturedPlaylistsResponse
import io.github.rubenquadros.kovibes.api.playlist.models.PlaylistTracksResponse
import io.github.rubenquadros.kovibes.api.response.ErrorBody
import io.ktor.client.request.get
import io.ktor.http.path
import io.ktor.util.StringValues
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @suppress
 * PlaylistApiImpl is the implementation of [PlaylistApi].
 *
 * @property ktorService
 * @property dispatcher
 */
internal class PlaylistApiImpl(
    private val ktorService: KtorService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : PlaylistApi {
    override suspend fun getFeaturedPlaylists(
        locale: String,
        limit: Int,
        offset: Int
    ): ApiResponse<FeaturedPlaylistsResponse, ErrorBody> {
        val response = withContext(dispatcher) {
            ktorService.client.get {
                url {
                    path("/v1/browse/featured-playlists")

                    parameters.appendAll(
                        StringValues.build {
                            this["locale"] = locale
                            this["limit"] = limit.toString()
                            this["offset"] = offset.toString()
                        }
                    )
                }
            }
        }

        return response.getParsedHttpResponse()
    }

    override suspend fun getPlaylist(
        id: String,
        market: String?,
        fields: List<String>?,
        additionalTypes: List<String>
    ): ApiResponse<PlaylistInfo, ErrorBody> {
        val response = withContext(dispatcher) {
            ktorService.client.get {
                url {
                    path("v1/playlists/$id")

                    parameters.appendAll(
                        StringValues.build {
                            market?.let { this["market"] = market }
                            fields?.let { this["fields"] = fields.joinToString { it } }
                            this["additional_types"] = additionalTypes.joinToString { it }
                        }
                    )
                }
            }
        }

        return response.getParsedHttpResponse()
    }

    override suspend fun getPlaylistTracks(
        id: String,
        market: String?,
        fields: List<String>?,
        additionalTypes: List<String>,
        limit: Int,
        offset: Int
    ): ApiResponse<PlaylistTracksResponse, ErrorBody> {
        val response = withContext(dispatcher) {
            ktorService.client.get {
                url {
                    path("v1/playlists/$id/tracks")

                    parameters.appendAll(
                        StringValues.build {
                            market?.let { this["market"] = market }
                            fields?.let { this["fields"] = fields.joinToString { it } }
                            this["additional_types"] = additionalTypes.joinToString { it }
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