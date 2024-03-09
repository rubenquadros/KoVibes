package com.ruben.spotify.api.playlist.models

import com.ruben.spotify.api.ExcludeFromCoverage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ExcludeFromCoverage
@Serializable
internal data class VideoThumbnailInfo(
    @SerialName("url")
    val url: String?
)
