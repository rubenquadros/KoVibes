package io.github.rubenquadros.kovibes.api.recommendations

import io.github.rubenquadros.kovibes.api.ApiResponse
import io.github.rubenquadros.kovibes.api.recommendations.models.RecommendationsResponse
import io.github.rubenquadros.kovibes.api.request.GetRecommendationsRequest
import io.github.rubenquadros.kovibes.api.response.ErrorBody

/**
 * Recommendations API interface provides methods using which one can get track recommendations.
 *
 * Each API returns [ApiResponse].
 */
internal interface RecommendationsApi {

    /**
     * Get recommendations API returns all the recommended tracks based on the input provided.
     *
     * @param getRecommendationsRequest
     * @return [RecommendationsResponse] when success and [ErrorBody] when error.
     */
    suspend fun getRecommendations(
        getRecommendationsRequest: GetRecommendationsRequest
    ): ApiResponse<RecommendationsResponse, ErrorBody>
}