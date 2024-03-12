package com.ruben.spotify.api.recommendations

import com.ruben.spotify.api.mapper.toTrack
import com.ruben.spotify.api.models.TrackInfo
import com.ruben.spotify.api.recommendations.models.RecommendationsResponse
import com.ruben.spotify.api.recommendations.models.SeedInfo
import com.ruben.spotify.api.response.Recommendations
import com.ruben.spotify.api.response.Seed

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