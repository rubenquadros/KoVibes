package com.ruben.spotify.api.models

import com.ruben.spotify.api.ExcludeFromCoverage
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