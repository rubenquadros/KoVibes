package com.ruben.spotify.api.test.service

import com.ruben.spotify.api.ApiResponse
import com.ruben.spotify.api.browse.BrowseApi
import com.ruben.spotify.api.browse.models.CategoriesResponse
import com.ruben.spotify.api.response.ErrorBody
import com.ruben.spotify.api.response.Genres
import com.ruben.spotify.api.test.getApiResponse
import com.ruben.spotify.api.test.getExpectedResponse
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