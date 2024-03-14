package io.github.rubenquadros.kovibes.api.browse

import io.github.rubenquadros.kovibes.api.browse.models.CategoriesResponse
import io.github.rubenquadros.kovibes.api.browse.models.CategoryInfo
import io.github.rubenquadros.kovibes.api.mapper.toImage
import io.github.rubenquadros.kovibes.api.models.ImageInfo
import io.github.rubenquadros.kovibes.api.response.Categories
import io.github.rubenquadros.kovibes.api.response.Category

/**
 * @suppress
 * Map [CategoriesResponse] to [Categories].
 */
internal fun CategoriesResponse.toCategories(): Categories {
    return Categories(
        isNext = this.categories.next != null,
        items = this.categories.items.map { categoryInfo: CategoryInfo ->
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