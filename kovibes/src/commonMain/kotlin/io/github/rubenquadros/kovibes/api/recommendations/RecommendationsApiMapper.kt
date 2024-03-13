package io.github.rubenquadros.kovibes.api.recommendations

import io.github.rubenquadros.kovibes.api.mapper.toTrack
import io.github.rubenquadros.kovibes.api.models.TrackInfo
import io.github.rubenquadros.kovibes.api.recommendations.models.RecommendationsResponse
import io.github.rubenquadros.kovibes.api.recommendations.models.SeedInfo
import io.github.rubenquadros.kovibes.api.response.Recommendations
import io.github.rubenquadros.kovibes.api.response.Seed

/**
 * @suppress
 * Map [RecommendationsResponse] to [Recommendations].
 */
internal fun RecommendationsResponse.toRecommendations(): Recommendations {
    return Recommendations(
        seeds = this.seeds.map { seedInfo: SeedInfo ->
            Seed(
                id = seedInfo.id,
                type = seedInfo.type
            )
        },
        tracks = this.tracks.map { trackInfo: TrackInfo ->  trackInfo.toTrack() }
    )
}