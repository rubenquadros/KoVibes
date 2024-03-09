package com.ruben.spotify.api.search.models

import com.ruben.spotify.api.ExcludeFromCoverage
import com.ruben.spotify.api.models.PlaylistInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ExcludeFromCoverage
@Serializable
internal data class SearchPlaylistResponse(
    @SerialName("playlists")
    val playlists: SearchPlaylistInfo
)

@ExcludeFromCoverage
@Serializable
internal data class SearchPlaylistInfo(
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
    val items: List<PlaylistInfo>
)
