package io.github.rubenquadros.kovibes.api.browse

import io.github.rubenquadros.kovibes.api.ApiResponse
import io.github.rubenquadros.kovibes.api.browse.models.CategoriesResponse
import io.github.rubenquadros.kovibes.api.response.ErrorBody
import io.github.rubenquadros.kovibes.api.response.Genres

/**
 * Browse API interface provides methods using which one can browse for content from the Spotify API.
 *
 * Each API returns [ApiResponse].
 */
internal interface BrowseApi {

    /**
     * Get genres API returns the available genres.
     *
     * @return [Genres] when success and [ErrorBody] when error.
     */
    suspend fun getGenres(): ApiResponse<Genres, ErrorBody>

    /**
     * Get categories API returns the available categories.
     *
     * @param locale
     * @param limit
     * @param offset
     * @return [CategoriesResponse] when success and [ErrorBody] when error.
     */
    suspend fun getCategories(
        locale: String,
        limit: Int,
        offset: Int
    ): ApiResponse<CategoriesResponse, ErrorBody>
}