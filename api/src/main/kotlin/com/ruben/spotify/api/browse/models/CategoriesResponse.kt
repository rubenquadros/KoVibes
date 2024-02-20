package com.ruben.spotify.api.browse.models

import com.ruben.spotify.api.playlist.models.ImageInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CategoriesResponse(
    @SerialName("categories")
    val categories: CategoriesInfo
)

@Serializable
internal data class CategoriesInfo(
    @SerialName("href")
    val href: String,
    @SerialName("limit")
    val limit: Int,
    @SerialName("offset")
    val offset: Int,
    @SerialName("next")
    val next: String? = null,
    @SerialName("previous")
    val previous: String? = null,
    @SerialName("total")
    val total: Int,
    @SerialName("items")
    val items: List<CategoryInfo>
)

@Serializable
internal data class CategoryInfo(
    @SerialName("href")
    val href: String,
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("icons")
    val icons: List<ImageInfo>
)