package io.github.rubenquadros.kovibes.api.test.service

import io.github.rubenquadros.kovibes.api.ApiResponse
import io.github.rubenquadros.kovibes.api.browse.BrowseApi
import io.github.rubenquadros.kovibes.api.browse.models.CategoriesResponse
import io.github.rubenquadros.kovibes.api.response.ErrorBody
import io.github.rubenquadros.kovibes.api.response.Genres
import io.github.rubenquadros.kovibes.api.test.getApiResponse
import io.github.rubenquadros.kovibes.api.test.getExpectedResponse
import kotlinx.serialization.json.Json

internal class FakeBrowseApi : BrowseApi {

    companion object {
        var isSuccess: Boolean = true
    }

    override suspend fun getGenres(): ApiResponse<Genres, ErrorBody> {
        return getApiResponse(isSuccess) {
            Json.decodeFromString(getExpectedResponse("browse/genres.json"))
        }
    }

    override suspend fun getCategories(
        locale: String,
        limit: Int,
        offset: Int
    ): ApiResponse<CategoriesResponse, ErrorBody> {
        return getApiResponse(isSuccess) {
            Json.decodeFromString(getExpectedResponse("browse/categories.json"))
        }
    }
}