package com.ruben.spotify.api.playlist.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
internal data class ExternalUrls(
    @SerialName("spotify")
    val spotify: String
)

@Serializable
internal data class ExternalIds(
    @SerialName("isrc")
    val isrc: String
)