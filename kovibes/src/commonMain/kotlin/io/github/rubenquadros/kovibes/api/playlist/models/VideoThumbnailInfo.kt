package io.github.rubenquadros.kovibes.api.playlist.models

import io.github.rubenquadros.kovibes.api.ExcludeFromCoverage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ExcludeFromCoverage
@Serializable
internal data class VideoThumbnailInfo(
    @SerialName("url")
    val url: String?
)
