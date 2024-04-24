package io.github.rubenquadros.kovibes.api.search.models

import io.github.rubenquadros.kovibes.api.ExcludeFromCoverage
import io.github.rubenquadros.kovibes.api.models.AlbumResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ExcludeFromCoverage
@Serializable
internal data class SearchAlbumResponse(
    @SerialName("albums")
    val albumResponse: AlbumResponse
)