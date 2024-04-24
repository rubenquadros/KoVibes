package io.github.rubenquadros.kovibes.api.artist.models

import io.github.rubenquadros.kovibes.api.ExcludeFromCoverage
import io.github.rubenquadros.kovibes.api.models.TrackInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ExcludeFromCoverage
@Serializable
internal data class GetArtistTopTracksResponse(
    @SerialName("tracks")
    val tracks: List<TrackInfo>
)