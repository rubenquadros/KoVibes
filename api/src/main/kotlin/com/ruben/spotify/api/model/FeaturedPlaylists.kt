package com.ruben.spotify.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeaturedPlaylists(
    @SerialName("playlists")
    val playlists: List<Playlist>
)

@Serializable
data class Playlist(
    @SerialName("collaborative")
    val collaborative: Boolean,
    @SerialName("description")
    val description: String,
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("images")
    val images: List<Image>,
    @SerialName("public")
    val public: Boolean
)

@Serializable
data class Image(
    @SerialName("height")
    val height: Int,
    @SerialName("width")
    val width: Int,
    @SerialName("url")
    val url: String
)
