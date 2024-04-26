package io.github.rubenquadros.kovibes.api.test.browse

import io.github.rubenquadros.kovibes.api.browse.models.CategoriesResponse
import io.github.rubenquadros.kovibes.api.browse.toCategories
import io.github.rubenquadros.kovibes.api.test.getExpectedResponse
import io.github.rubenquadros.kovibes.api.test.json
import kotlin.test.Test
import kotlin.test.assertTrue

class BrowseApiMapperTest {

    @Test
    fun `categories response is mapped to categories`() {
        val categoriesResponseJson = json.decodeFromString<CategoriesResponse>(
            getExpectedResponse("browse/categories.json")
        )

        val categories = categoriesResponseJson.toCategories()

        assertTrue {
            categories.items.size == categoriesResponseJson.categories.items.size
        }
    }
}