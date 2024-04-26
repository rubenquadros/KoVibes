package io.github.rubenquadros.kovibes.api.test.recommendations

import io.github.rubenquadros.kovibes.api.recommendations.models.RecommendationsResponse
import io.github.rubenquadros.kovibes.api.recommendations.toRecommendations
import io.github.rubenquadros.kovibes.api.test.getExpectedResponse
import io.github.rubenquadros.kovibes.api.test.json
import kotlin.test.Test
import kotlin.test.assertTrue

class RecommendationsApiMapperTest {

    @Test
    fun `recommendations response is mapped to recommendations`() {
        val recommendationsResponseJson = json.decodeFromString<RecommendationsResponse>(
            getExpectedResponse("recommendations/recommendations.json")
        )

        val recommendations = recommendationsResponseJson.toRecommendations()

        assertTrue {
            recommendations.tracks.size == recommendationsResponseJson.tracks.size
        }
    }

}