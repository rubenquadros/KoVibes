package io.github.rubenquadros.kovibes.api.response

data class PlaylistTracks(
    val tracks: List<Track>,
    val isNext: Boolean
)
