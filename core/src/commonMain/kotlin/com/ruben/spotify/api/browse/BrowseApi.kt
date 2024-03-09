package com.ruben.spotify.api.browse

import com.ruben.spotify.api.ApiResponse
import com.ruben.spotify.api.browse.models.CategoriesResponse
import com.ruben.spotify.api.response.ErrorBody
import com.ruben.spotify.api.response.Genres

internal interface BrowseApi {

    suspend fun getGenres(): ApiResponse<Genres, ErrorBody>

    suspend fun getCategories(
        locale: String,
        limit: Int,
        offset: Int
    ): ApiResponse<CategoriesResponse, ErrorBody>
}