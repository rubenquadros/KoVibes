package com.ruben.spotify.api.recommendations

import com.ruben.spotify.api.ApiResponse
import com.ruben.spotify.api.recommendations.models.RecommendationsResponse
import com.ruben.spotify.api.request.GetRecommendationsRequest
import com.ruben.spotify.api.response.ErrorBody

internal interface RecommendationsApi {

    suspend fun getRecommendations(
        getRecommendationsRequest: GetRecommendationsRequest
    ): ApiResponse<RecommendationsResponse, ErrorBody>
}