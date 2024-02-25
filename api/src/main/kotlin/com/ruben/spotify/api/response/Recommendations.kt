package com.ruben.spotify.api.response

data class Recommendations(
    val tracks: List<Track>,
    val seeds: List<Seed>
)

data class Seed(
    val id: String,
    val type: String
)
