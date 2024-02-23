package com.ruben.spotify.api.models

import com.ruben.spotify.api.ExcludeFromCoverage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ExcludeFromCoverage
@Serializable
internal data class ImageInfo(
    @SerialName("height")
    val height: Int?,
    @SerialName("width")
    val width: Int?,
    @SerialName("url")
    val url: String?
)
