package io.github.rubenquadros.kovibes.api.album.models

import io.github.rubenquadros.kovibes.api.ExcludeFromCoverage
import io.github.rubenquadros.kovibes.api.models.ArtistInfo
import io.github.rubenquadros.kovibes.api.models.ExternalUrls
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ExcludeFromCoverage
@Serializable
internal data class GetAlbumTracksResponse(
    @SerialName("href")
    val href: String,
    @SerialName("limit")
    val limit: Int,
    @SerialName("next")
    val next: String?,
    @SerialName("offset")
    val offset: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("previous")
    val previous: String?,
    @SerialName("items")
    val items: List<SimplifiedTrackInfo>
)

@ExcludeFromCoverage
@Serializable
internal data class SimplifiedTrackInfo(
    @SerialName("artists")
    val artists: List<ArtistInfo>,
    @SerialName("available_markets")
    val availableMarkets: List<String>,
    @SerialName("disc_number")
    val discNumber: Int,
    @SerialName("duration_ms")
    val duration: Long,
    @SerialName("explicit")
    val explicit: Boolean,
    @SerialName("external_urls")
    val externalUrls: ExternalUrls,
    @SerialName("href")
    val href: String,
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("preview_url")
    val previewUrl: String? = null,
    @SerialName("track_number")
    val trackNumber: Int,
    @SerialName("type")
    val type: String,
    @SerialName("uri")
    val uri: String
)


