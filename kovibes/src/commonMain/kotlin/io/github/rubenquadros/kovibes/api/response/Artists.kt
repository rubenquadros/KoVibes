package io.github.rubenquadros.kovibes.api.response

data class Artists(
    val items: List<Artist>,
    val isNext: Boolean
)

data class Artist(
    val followers: Long?,
    val genres: List<String>?,
    val id: String,
    val images: List<Image>?,
    val name: String,
    val popularity: Int
)
