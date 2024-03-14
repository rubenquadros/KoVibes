package io.github.rubenquadros.kovibes.api.search

import io.github.rubenquadros.kovibes.api.ApiResponse
import io.github.rubenquadros.kovibes.api.KtorService
import io.github.rubenquadros.kovibes.api.getParsedHttpResponse
import io.github.rubenquadros.kovibes.api.response.ErrorBody
import io.github.rubenquadros.kovibes.api.search.models.SearchAlbumResponse
import io.github.rubenquadros.kovibes.api.search.models.SearchArtistResponse
import io.github.rubenquadros.kovibes.api.search.models.SearchPlaylistResponse
import io.github.rubenquadros.kovibes.api.search.models.SearchTrackResponse
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.path
import io.ktor.util.StringValues
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @suppress
 * SearchApiImpl is the implementation of [SearchApi]
 *
 * @property ktorService
 * @property dispatcher
 */
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

        return response.getParsedHttpResponse()
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

        return response.getParsedHttpResponse()
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

        return response.getParsedHttpResponse()
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


    /**
     * @suppress
     * Wrapper around search network call.
     *
     * @param query
     * @param market
     * @param limit
     * @param offset
     * @param type
     * @return [HttpResponse].
     */
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