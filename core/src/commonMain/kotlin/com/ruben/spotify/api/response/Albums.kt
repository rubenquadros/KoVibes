package com.ruben.spotify.api.response

data class Albums(
    val items: List<Album>,
    val isNext: Boolean
)

data class Album(
    val albumType: String,
    val artists: List<Artist>,
    val availableMarkets: List<String>?,
    val id: String,
    val images: List<Image>,
    val name: String,
    val releaseDate: String,
    val restrictions: Restrictions?,
    val totalTracks: Int
)
