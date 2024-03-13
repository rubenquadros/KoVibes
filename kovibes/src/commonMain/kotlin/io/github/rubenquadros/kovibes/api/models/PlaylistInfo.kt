package io.github.rubenquadros.kovibes.api.models

import io.github.rubenquadros.kovibes.api.ExcludeFromCoverage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ExcludeFromCoverage
@Serializable
internal data class PlaylistInfo(
    @SerialName("collaborative")
    val collaborative: Boolean,
    @SerialName("description")
    val description: String?,
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
    @SerialName("owner")
    val owner: PlaylistOwner,
    @SerialName("primary_color")
    val primaryColor: String?,
    @SerialName("public")
    val public: Boolean? = null,
    @SerialName("snapshot_id")
    val snapshotId: String,
    @SerialName("tracks")
    val tracks: PlaylistTracks,
    @SerialName("type")
    val type: String,
    @SerialName("uri")
    val uri: String
)

@ExcludeFromCoverage
@Serializable
internal data class PlaylistOwner(
    @SerialName("display_name")
    val displayName: String,
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

@ExcludeFromCoverage
@Serializable
internal data class PlaylistTracks(
    @SerialName("href")
    val href: String,
    @SerialName("total")
    val total: Int
)