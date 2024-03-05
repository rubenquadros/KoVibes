package com.ruben.spotify.api.test.service

import com.ruben.spotify.api.ApiResponse
import com.ruben.spotify.api.recommendations.RecommendationsApi
import com.ruben.spotify.api.recommendations.models.RecommendationsResponse
import com.ruben.spotify.api.request.GetRecommendationsRequest
import com.ruben.spotify.api.response.ErrorBody
import com.ruben.spotify.api.test.getApiResponse
import com.ruben.spotify.api.test.getExpectedResponse
import kotlinx.serialization.json.Json

internal class FakeRecommendationsApi : RecommendationsApi {

    companion object {
        var isSuccess: Boolean = true
    }

    override suspend fun getRecommendations(
        getRecommendationsRequest: GetRecommendationsRequest
    ): ApiResponse<RecommendationsResponse, ErrorBody> {
        return getApiResponse(isSuccess) {
            Json.decodeFromString(getExpectedResponse("recommendations/recommendations.json"))
        }
    }
}