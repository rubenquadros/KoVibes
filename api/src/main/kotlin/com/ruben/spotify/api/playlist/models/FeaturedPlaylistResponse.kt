package com.ruben.spotify.api.playlist.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class FeaturedPlaylistsResponse(
    @SerialName("message")
    val message: String,
    @SerialName("playlists")
    val playlists: Playlists
)

@Serializable
internal data class Playlists(
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
    val items: List<PlaylistItem>
)

@Serializable
internal data class PlaylistItem(
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
    val public: Boolean,
    @SerialName("snapshot_id")
    val snapshotId: String,
    @SerialName("tracks")
    val tracks: PlaylistTracks,
    @SerialName("type")
    val type: String,
    @SerialName("uri")
    val uri: String
)

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

@Serializable
internal data class PlaylistTracks(
    @SerialName("href")
    val href: String,
    @SerialName("total")
    val total: Int
)