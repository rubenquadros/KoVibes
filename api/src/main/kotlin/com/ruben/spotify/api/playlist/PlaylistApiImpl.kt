package com.ruben.spotify.api.playlist

import com.ruben.spotify.api.ApiResponse
import com.ruben.spotify.api.KtorService
import com.ruben.spotify.api.getParsedHttpResponse
import com.ruben.spotify.api.playlist.models.FeaturedPlaylistsResponse
import com.ruben.spotify.api.playlist.models.PlaylistTracksResponse
import com.ruben.spotify.api.response.ErrorBody
import io.ktor.client.request.get
import io.ktor.http.parameters
import io.ktor.http.path
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
                }
                parameters {
                    this["locale"] = locale
                    this["limit"] = limit.toString()
                    this["offset"] = offset.toString()
                }
            }
        }

        return response.getParsedHttpResponse<FeaturedPlaylistsResponse, ErrorBody>()
    }

    override suspend fun getPlaylistTracks(
        id: String,
        market: String?,
        fields: String?,
        limit: Int,
        offset: Int
    ): ApiResponse<PlaylistTracksResponse, ErrorBody> {
        val response = withContext(dispatcher) {
            ktorService.client.get {
                url {
                    path("v1/playlists/$id/tracks")
                }
                parameters {
                    market?.let { this["market"] = market }
                    fields?.let { this["fields"] = fields }
                    this["limit"] = limit.toString()
                    this["offset"] = offset.toString()
                }
            }
        }

        return response.getParsedHttpResponse<PlaylistTracksResponse, ErrorBody>()
    }
}