package io.github.rubenquadros.kovibes.api.test.browse

import io.github.rubenquadros.kovibes.api.browse.BrowseApiImpl
import io.github.rubenquadros.kovibes.api.test.MockKtorService
import io.github.rubenquadros.kovibes.api.test.MockResponse
import io.github.rubenquadros.kovibes.api.test.assertApiResponseFailure
import io.github.rubenquadros.kovibes.api.test.assertApiResponseSuccess
import io.github.rubenquadros.kovibes.api.test.errorResponsePath
import kotlinx.coroutines.test.runTest
import org.junit.Test

class BrowseApiTest {

    private val browseApi = BrowseApiImpl(
        ktorService = MockKtorService.createMockKtorService(createMockBrowseConfig())
    )

    @Test
    fun `when genres spotify api responds success then result is received`() = runTest {
        MockKtorService.isSuccess = true

        val response = browseApi.getGenres()

        response.assertApiResponseSuccess(
            { response.result!!.genres.isNotEmpty() }
        )
    }

    @Test
    fun `when genres spotify api responds error then failure is received`() = runTest {
        MockKtorService.isSuccess = false

        val response = browseApi.getGenres()

        response.assertApiResponseFailure()
    }

    @Test
    fun `when categories spotify api responds success then result is received`() = runTest {
        MockKtorService.isSuccess = true

        val response = browseApi.getCategories(locale = "en_US", limit = 20, offset = 0)

        response.assertApiResponseSuccess(
            { response.result!!.categories.items.isNotEmpty() }
        )
    }

    @Test
    fun `when categories spotify api responds error then failure is received`() = runTest {
        MockKtorService.isSuccess = false

        val response = browseApi.getCategories(locale = "en_US", limit = 20, offset = 0)

        response.assertApiResponseFailure()
    }

    private fun createMockBrowseConfig() = mapOf(
        "available-genre-seeds" to MockResponse(
            expectedSuccessResponsePath = "browse/genres.json",
            expectedErrorResponsePath = errorResponsePath
        ),
        "browse/categories" to MockResponse(
            expectedSuccessResponsePath = "browse/categories.json",
            expectedErrorResponsePath = errorResponsePath
        )
    )
}