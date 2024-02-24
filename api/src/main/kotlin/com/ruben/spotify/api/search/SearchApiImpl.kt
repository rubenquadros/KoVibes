package com.ruben.spotify.api.search

import com.ruben.spotify.api.ApiResponse
import com.ruben.spotify.api.KtorService
import com.ruben.spotify.api.getParsedHttpResponse
import com.ruben.spotify.api.response.ErrorBody
import com.ruben.spotify.api.search.models.SearchAlbumResponse
import com.ruben.spotify.api.search.models.SearchArtistResponse
import com.ruben.spotify.api.search.models.SearchPlaylistResponse
import com.ruben.spotify.api.search.models.SearchTrackResponse
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.path
import io.ktor.util.StringValues
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class SearchApiImpl(
    private val ktorService: KtorService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : SearchApi {
    override suspend fun searchTrack(
        query: String,
        market: String?,
        limit: Int,
        offset: Int
    ): ApiResponse<SearchTrackResponse, ErrorBody> {
        val response = withContext(dispatcher) {
            searchSpotify(
                query = query, market = market, limit = limit, offset = offset, type = "track"
            )
        }

        return response.getParsedHttpResponse<SearchTrackResponse, ErrorBody>()
    }

    override suspend fun searchAlbum(
        query: String,
        market: String?,
        limit: Int,
        offset: Int
    ): ApiResponse<SearchAlbumResponse, ErrorBody> {
        val response = withContext(dispatcher) {
            searchSpotify(
                query = query, market = market, limit = limit, offset = offset, type = "album"
            )
        }

        return response.getParsedHttpResponse<SearchAlbumResponse, ErrorBody>()
    }

    override suspend fun searchArtist(
        query: String,
        market: String?,
        limit: Int,
        offset: Int
    ): ApiResponse<SearchArtistResponse, ErrorBody> {
        val response = withContext(dispatcher) {
            searchSpotify(
                query = query, market = market, limit = limit, offset = offset, type = "artist"
            )
        }

        return response.getParsedHttpResponse<SearchArtistResponse, ErrorBody>()
    }

    override suspend fun searchPlaylist(
        query: String,
        market: String?,
        limit: Int,
        offset: Int
    ): ApiResponse<SearchPlaylistResponse, ErrorBody> {
        val response = withContext(dispatcher) {
            searchSpotify(
                query = query, market = market, limit = limit, offset = offset, type = "playlist"
            )
        }

        return response.getParsedHttpResponse<SearchPlaylistResponse, ErrorBody>()
    }


    private suspend fun searchSpotify(
        query: String,
        market: String?,
        limit: Int,
        offset: Int,
        type: String
    ): HttpResponse {
        return ktorService.client.get {
            url {
                path("v1/search")

                parameters.appendAll(
                    StringValues.build {
                        this["q"] = query
                        market?.let { this["market"] = market }
                        this["limit"] = limit.toString()
                        this["offset"] = offset.toString()
                        this["type"] = type
                    }
                )
            }
        }
    }
}