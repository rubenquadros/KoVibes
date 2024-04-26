package io.github.rubenquadros.kovibes.api.response

data class AlbumTracks(
    val isNext: Boolean,
    val items: List<AlbumTrack>
)

data class AlbumTrack(
    val id: String,
    val name: String,
    val duration: Long,
    val artists: List<Artist>,
    val previewUrl: String?
)