package io.github.rubenquadros.kovibes.api.response

data class NewAlbumReleases(
    val isNext: Boolean,
    val items: List<Album>
)
