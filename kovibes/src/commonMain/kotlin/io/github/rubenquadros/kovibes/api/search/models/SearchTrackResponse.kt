package io.github.rubenquadros.kovibes.api.search.models

import io.github.rubenquadros.kovibes.api.ExcludeFromCoverage
import io.github.rubenquadros.kovibes.api.models.TrackInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ExcludeFromCoverage
@Serializable
internal data class SearchTrackResponse(
    @SerialName("tracks")
    val tracks: SearchTrackInfo
)

@ExcludeFromCoverage
@Serializable
internal data class SearchTrackInfo(
    @SerialName("href")
    val href: String,
    @SerialName("limit")
    val limit: Int,
    @SerialName("offset")
    val offset: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("previous")
    val previous: String? = null,
    @SerialName("next")
    val next: String? = null,
    @SerialName("items")
    val items: List<TrackInfo>
)