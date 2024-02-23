package com.ruben.spotify.api.playlist.models

import com.ruben.spotify.api.ExcludeFromCoverage
import com.ruben.spotify.api.models.ImageInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ExcludeFromCoverage
@Serializable
internal data class TrackInfo(
    @SerialName("album")
    val albumInfo: AlbumInfo,
    @SerialName("artists")
    val artistsInfo: List<ArtistInfo>,
    @SerialName("available_markets")
    val availableMarkets: List<String>,
    @SerialName("disc_number")
    val discNumber: Int,
    @SerialName("duration_ms")
    val discDuration: Long,
    @SerialName("episode")
    val episode: Boolean?,
    @SerialName("explicit")
    val explicit: Boolean,
    @SerialName("external_ids")
    val externalIds: ExternalIds,
    @SerialName("external_urls")
    val externalUrls: ExternalUrls,
    @SerialName("href")
    val href: String,
    @SerialName("id")
    val id: String,
    @SerialName("is_playable")
    val isPlayable: Boolean? = null,
    @SerialName("is_local")
    val isLocal: Boolean,
    @SerialName("name")
    val name: String,
    @SerialName("popularity")
    val popularity: Int,
    @SerialName("restrictions")
    val restrictions: RestrictionInfo? = null,
    @SerialName("preview_url")
    val previewUrl: String?,
    @SerialName("track")
    val track: Boolean?,
    @SerialName("track_number")
    val trackNumber: Int,
    @SerialName("type")
    val type: String,
    @SerialName("uri")
    val uri: String
)

@ExcludeFromCoverage
@Serializable
internal data class AlbumInfo(
    @SerialName("album_type")
    val albumType: String,
    @SerialName("artists")
    val artists: List<ArtistInfo>,
    @SerialName("available_markets")
    val availableMarkets: List<String>,
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
    val uri: String
)

@ExcludeFromCoverage
@Serializable
internal data class ArtistInfo(
    @SerialName("external_urls")
    val externalUrls: ExternalUrls,
    @SerialName("followers")
    val followers: FollowersInfo? = null,
    @SerialName("genres")
    val genres: List<String>? = null,
    @SerialName("href")
    val href: String,
    @SerialName("id")
    val id: String,
    @SerialName("images")
    val images: List<ImageInfo>? = null,
    @SerialName("name")
    val name: String,
    @SerialName("popularity")
    val popularity: Int? = null,
    @SerialName("type")
    val type: String,
    @SerialName("uri")
    val uri: String
)

@ExcludeFromCoverage
@Serializable
internal data class FollowersInfo(
    @SerialName("href")
    val href: String?,
    @SerialName("total")
    val total: Long
)

@ExcludeFromCoverage
@Serializable
internal data class RestrictionInfo(
    @SerialName("reason")
    val reason: String
)
