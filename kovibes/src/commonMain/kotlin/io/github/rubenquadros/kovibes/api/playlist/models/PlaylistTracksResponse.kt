package io.github.rubenquadros.kovibes.api.playlist.models

import io.github.rubenquadros.kovibes.api.ExcludeFromCoverage
import io.github.rubenquadros.kovibes.api.models.ExternalUrls
import io.github.rubenquadros.kovibes.api.models.TrackInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ExcludeFromCoverage
@Serializable
internal data class PlaylistTracksResponse(
    @SerialName("href")
    val href: String,
    @SerialName("limit")
    val limit: Int,
    @SerialName("next")
    val next: String?,
    @SerialName("offset")
    val offset: Int,
    @SerialName("previous")
    val previous: String?,
    @SerialName("total")
    val total: Int,
    @SerialName("items")
    val items: List<TrackItem>
)

@ExcludeFromCoverage
@Serializable
internal data class TrackItem(
    @SerialName("added_at")
    val addedAt: String?,
    @SerialName("is_local")
    val isLocal: Boolean,
    @SerialName("added_by")
    val addedBy: AdditionalTrackInfo?,
    @SerialName("primary_color")
    val primaryColor: String?,
    @SerialName("track")
    val trackInfo: TrackInfo,
    @SerialName("video_thumbnail")
    val videoThumbnail: VideoThumbnailInfo?
)

@ExcludeFromCoverage
@Serializable
internal data class AdditionalTrackInfo(
    @SerialName("external_urls")
    val externalUrls: ExternalUrls,
    @SerialName("href")
    val href: String,
    @SerialName("id")
    val id: String,
    @SerialName("type")
    val type: String,
    @SerialName("uri")
    val uri: String
)