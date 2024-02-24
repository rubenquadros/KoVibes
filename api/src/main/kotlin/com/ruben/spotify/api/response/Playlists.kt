package com.ruben.spotify.api.response

data class Playlists(
    val items: List<Playlist>,
    val isNext: Boolean
)

data class Playlist(
    val collaborative: Boolean,
    val description: String,
    val id: String,
    val name: String,
    val images: List<Image>,
    val public: Boolean
)
