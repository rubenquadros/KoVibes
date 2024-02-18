package com.ruben.spotify.api.response

data class PlaylistTracks(
    val tracks: List<Track>,
    val isNext: Boolean
)
