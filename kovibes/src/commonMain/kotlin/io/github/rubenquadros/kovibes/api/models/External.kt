package io.github.rubenquadros.kovibes.api.models

import io.github.rubenquadros.kovibes.api.ExcludeFromCoverage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ExcludeFromCoverage
@Serializable
internal data class ExternalUrls(
    @SerialName("spotify")
    val spotify: String
)

@ExcludeFromCoverage
@Serializable
internal data class ExternalIds(
    @SerialName("isrc")
    val isrc: String
)