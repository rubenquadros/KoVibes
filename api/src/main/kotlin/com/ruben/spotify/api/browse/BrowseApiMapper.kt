package com.ruben.spotify.api.browse

import com.ruben.spotify.api.browse.models.CategoriesResponse
import com.ruben.spotify.api.browse.models.CategoryInfo
import com.ruben.spotify.api.mapper.toImage
import com.ruben.spotify.api.playlist.models.ImageInfo
import com.ruben.spotify.api.response.Categories
import com.ruben.spotify.api.response.Category

internal fun CategoriesResponse.toCategories(): Categories {
    return Categories(
        isNext = this.categories.next != null,
        categories = this.categories.items.map { categoryInfo: CategoryInfo ->
            Category(
                name = categoryInfo.name,
                id = categoryInfo.id,
                image = categoryInfo.icons.map { imageInfo: ImageInfo ->
                    imageInfo.toImage()
                }
            )
        }
    )
}