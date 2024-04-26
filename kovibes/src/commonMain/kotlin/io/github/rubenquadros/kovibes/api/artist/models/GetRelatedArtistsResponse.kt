package io.github.rubenquadros.kovibes.api.artist.models

import io.github.rubenquadros.kovibes.api.ExcludeFromCoverage
import io.github.rubenquadros.kovibes.api.models.ArtistInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ExcludeFromCoverage
@Serializable
internal data class GetRelatedArtistsResponse(
    @SerialName("artists")
    val artists: List<ArtistInfo>
)