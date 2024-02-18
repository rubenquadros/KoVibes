package com.ruben.spotify.api.playlist.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ImageInfo(
    @SerialName("height")
    val height: Int?,
    @SerialName("width")
    val width: Int?,
    @SerialName("url")
    val url: String?
)
