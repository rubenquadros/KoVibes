package com.ruben.spotify.api.response

data class Artist(
    val followers: Long?,
    val genres: List<String>?,
    val id: String,
    val images: List<Image>?,
    val name: String,
    val popularity: Int
)
