package io.github.rubenquadros.kovibes.api.test.browse

import io.github.rubenquadros.kovibes.api.browse.models.CategoriesResponse
import io.github.rubenquadros.kovibes.api.browse.toCategories
import io.github.rubenquadros.kovibes.api.test.getExpectedResponse
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.assertTrue

class BrowseApiMapperTest {

    @Test
    fun `categories response is mapped to categories`() {
        val categoriesResponseJson = Json.decodeFromString<CategoriesResponse>(
            getExpectedResponse("browse/categories.json")
        )

        val categories = categoriesResponseJson.toCategories()

        assertTrue {
            categories.items.size == categoriesResponseJson.categories.items.size
        }
    }
}