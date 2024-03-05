package com.ruben.spotify.api.test.recommendations

import com.ruben.spotify.api.recommendations.models.RecommendationsResponse
import com.ruben.spotify.api.recommendations.toRecommendations
import com.ruben.spotify.api.test.getExpectedResponse
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.assertTrue

class RecommendationsApiMapperTest {

    @Test
    fun `recommendations response is mapped to recommendations`() {
        val recommendationsResponseJson = Json.decodeFromString<RecommendationsResponse>(
            getExpectedResponse("recommendations/recommendations.json")
        )

        val recommendations = recommendationsResponseJson.toRecommendations()
        assertTrue {
            recommendations.tracks.size == recommendationsResponseJson.tracks.size
        }
    }

}