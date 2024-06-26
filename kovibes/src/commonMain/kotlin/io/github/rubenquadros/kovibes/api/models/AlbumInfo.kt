package io.github.rubenquadros.kovibes.api.models

import io.github.rubenquadros.kovibes.api.ExcludeFromCoverage
import io.github.rubenquadros.kovibes.api.playlist.models.RestrictionInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ExcludeFromCoverage
@Serializable
internal data class AlbumResponse(
    @SerialName("href")
    val href: String,
    @SerialName("limit")
    val limit: Int,
    @SerialName("offset")
    val offset: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("previous")
    val previous: String? = null,
    @SerialName("next")
    val next: String? = null,
    @SerialName("items")
    val items: List<AlbumInfo>
)

@ExcludeFromCoverage
@Serializable
internal data class AlbumInfo(
    @SerialName("album_group")
    val albumGroup: String? = null,
    @SerialName("album_type")
    val albumType: String,
    @SerialName("artists")
    val artists: List<ArtistInfo>,
    @SerialName("available_markets")
    val availableMarkets: List<String>? = null,
    @SerialName("external_urls")
    val externalUrls: ExternalUrls,
    @SerialName("href")
    val href: String,
    @SerialName("id")
    val id: String,
    @SerialName("images")
    val images: List<ImageInfo>,
    @SerialName("name")
    val name: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("release_date_precision")
    val releaseDatePrecision: String,
    @SerialName("restrictions")
    val restrictions: RestrictionInfo? = null,
    @SerialName("total_tracks")
    val totalTracks: Int,
    @SerialName("type")
    val type: String,
    @SerialName("uri")
    val uri: String,
    @SerialName("genres")
    val genres: List<String>? = null,
    @SerialName("label")
    val label: String? = null
)
