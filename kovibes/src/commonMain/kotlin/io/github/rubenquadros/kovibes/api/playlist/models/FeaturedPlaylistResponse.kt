package io.github.rubenquadros.kovibes.api.playlist.models

import io.github.rubenquadros.kovibes.api.ExcludeFromCoverage
import io.github.rubenquadros.kovibes.api.models.PlaylistInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ExcludeFromCoverage
@Serializable
internal data class FeaturedPlaylistsResponse(
    @SerialName("message")
    val message: String,
    @SerialName("playlists")
    val playlists: Playlists
)

@ExcludeFromCoverage
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
    val items: List<PlaylistInfo>
)