package com.ruben.spotify.api.playlist.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class VideoThumbnailInfo(
    @SerialName("url")
    val url: String?
)
