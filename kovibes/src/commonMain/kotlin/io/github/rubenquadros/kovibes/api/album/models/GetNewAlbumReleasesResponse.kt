package io.github.rubenquadros.kovibes.api.album.models

import io.github.rubenquadros.kovibes.api.ExcludeFromCoverage
import io.github.rubenquadros.kovibes.api.models.AlbumInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ExcludeFromCoverage
@Serializable
internal data class GetNewAlbumReleasesResponse(
    @SerialName("albums")
    val albums: NewAlbumsInfo
)

@ExcludeFromCoverage
@Serializable
internal data class NewAlbumsInfo(
    @SerialName("href")
    val href: String,
    @SerialName("limit")
    val limit: Int,
    @SerialName("next")
    val next: String?,
    @SerialName("offset")
    val offset: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("previous")
    val previous: String?,
    @SerialName("items")
    val items: List<AlbumInfo>
)
