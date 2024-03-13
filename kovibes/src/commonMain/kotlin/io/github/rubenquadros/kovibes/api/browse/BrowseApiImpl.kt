package io.github.rubenquadros.kovibes.api.browse

import io.github.rubenquadros.kovibes.api.ApiResponse
import io.github.rubenquadros.kovibes.api.KtorService
import io.github.rubenquadros.kovibes.api.browse.models.CategoriesResponse
import io.github.rubenquadros.kovibes.api.getParsedHttpResponse
import io.github.rubenquadros.kovibes.api.response.ErrorBody
import io.github.rubenquadros.kovibes.api.response.Genres
import io.ktor.client.request.get
import io.ktor.http.path
import io.ktor.util.StringValues
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @suppress
 * BrowseApiImpl is the implementation of [BrowseApi].
 *
 * @property ktorService
 * @property dispatcher
 */
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

        return response.getParsedHttpResponse()
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

        return response.getParsedHttpResponse()
    }
}