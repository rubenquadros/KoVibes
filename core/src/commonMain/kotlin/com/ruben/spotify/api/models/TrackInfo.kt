package com.ruben.spotify.api.models

import com.ruben.spotify.api.ExcludeFromCoverage
import com.ruben.spotify.api.playlist.models.AdditionalTrackInfo
import com.ruben.spotify.api.playlist.models.RestrictionInfo
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
    val availableMarkets: List<String>? = null,
    @SerialName("disc_number")
    val discNumber: Int,
    @SerialName("duration_ms")
    val discDuration: Long,
    @SerialName("episode")
    val episode: Boolean? = null,
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
    val previewUrl: String? = null,
    @SerialName("track")
    val track: Boolean? = null,
    @SerialName("track_number")
    val trackNumber: Int,
    @SerialName("type")
    val type: String,
    @SerialName("uri")
    val uri: String,
    @SerialName("linked_from")
    val linkedFrom: AdditionalTrackInfo? = null
)
