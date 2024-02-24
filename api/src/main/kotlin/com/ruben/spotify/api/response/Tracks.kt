package com.ruben.spotify.api.response

data class Tracks(
    val items: List<Track>,
    val isNext: Boolean
)

data class Track(
    val addedAt: String?,
    val album: Album,
    val artists: List<Artist>,
    val availableMarkets: List<String>,
    val restrictions: Restrictions?,
    val discNumber: Int,
    val duration: Long,
    val explicit: Boolean,
    val id: String,
    val name: String,
    val popularity: Int,
    val previewUrl: String?
)
