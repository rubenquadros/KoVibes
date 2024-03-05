package com.ruben.spotify.api.recommendations.models

import com.ruben.spotify.api.ExcludeFromCoverage
import com.ruben.spotify.api.models.TrackInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ExcludeFromCoverage
@Serializable
internal data class RecommendationsResponse(
    @SerialName("seeds")
    val seeds: List<SeedInfo>,
    @SerialName("tracks")
    val tracks: List<TrackInfo>
)

@ExcludeFromCoverage
@Serializable
internal data class SeedInfo(
    @SerialName("href")
    val href: String,
    @SerialName("id")
    val id: String,
    @SerialName("type")
    val type: String,
    @SerialName("initialPoolSize")
    val initialPoolSize: Int,
    @SerialName("afterFilteringSize")
    val afterFilteringSize: Int,
    @SerialName("afterRelinkingSize")
    val afterRelinkingSize: Int
)