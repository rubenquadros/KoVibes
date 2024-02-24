package com.ruben.spotify.api.browse

import com.ruben.spotify.api.ApiResponse
import com.ruben.spotify.api.KtorService
import com.ruben.spotify.api.browse.models.CategoriesResponse
import com.ruben.spotify.api.getParsedHttpResponse
import com.ruben.spotify.api.response.ErrorBody
import com.ruben.spotify.api.response.Genres
import io.ktor.client.request.get
import io.ktor.http.path
import io.ktor.util.StringValues
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class BrowseApiImpl(
    private val ktorService: KtorService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BrowseApi {
    override suspend fun getGenres(): ApiResponse<Genres, ErrorBody> {
        val response = withContext(dispatcher) {
            ktorService.client.get {
                url {
                    path("v1/recommendations/available-genre-seeds")
                }
            }
        }

        return response.getParsedHttpResponse<Genres, ErrorBody>()
    }

    override suspend fun getCategories(
        locale: String,
        limit: Int,
        offset: Int
    ): ApiResponse<CategoriesResponse, ErrorBody> {
        val response = withContext(dispatcher) {
            ktorService.client.get {
                url {
                    path("v1/browse/categories")

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

        return response.getParsedHttpResponse<CategoriesResponse, ErrorBody>()
    }
}