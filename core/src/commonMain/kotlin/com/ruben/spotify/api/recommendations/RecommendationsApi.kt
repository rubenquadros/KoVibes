package com.ruben.spotify.api.recommendations

import com.ruben.spotify.api.ApiResponse
import com.ruben.spotify.api.recommendations.models.RecommendationsResponse
import com.ruben.spotify.api.request.GetRecommendationsRequest
import com.ruben.spotify.api.response.ErrorBody

/**
 * Recommendations API interface provides methods using which one can get track recommendations.
 *
 * Each API returns [ApiResponse].
 */
internal interface RecommendationsApi {

    /**
     * Get recommendations returns all the recommended tracks based on the input provided.
     *
     * @param getRecommendationsRequest
     * @return [RecommendationsResponse] when success and [ErrorBody] when error.
     */
    suspend fun getRecommendations(
        getRecommendationsRequest: GetRecommendationsRequest
    ): ApiResponse<RecommendationsResponse, ErrorBody>
}