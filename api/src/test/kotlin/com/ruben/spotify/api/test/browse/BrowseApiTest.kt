package com.ruben.spotify.api.test.browse

import com.ruben.spotify.api.browse.BrowseApiImpl
import com.ruben.spotify.api.test.MockKtorService
import com.ruben.spotify.api.test.MockResponse
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class BrowseApiTest {

    private val browseApi = BrowseApiImpl(
        ktorService = MockKtorService.createMockKtorService(createMockConfig())
    )

    @Test
    fun `when genres spotify api responds success then result is received`() = runTest {
        MockKtorService.isSuccess = true

        val successResponse = browseApi.getGenres()

        assert(successResponse.failure == null)
        assert(successResponse.result?.genres?.isNotEmpty() == true)
    }

    @Test
    fun `when genres spotify api responds error then failure is received`() = runTest {
        MockKtorService.isSuccess = false

        val errorResponse = browseApi.getGenres()

        assert(errorResponse.result == null)
        assert(errorResponse.failure?.error?.status == 500)
    }

    @Test
    fun `when categories spotify api responds success then result is received`() = runTest {
        MockKtorService.isSuccess = true

        val successResponse = browseApi.getCategories(locale = "en_US", limit = 20, offset = 0)

        assert(successResponse.failure == null)
        assert(successResponse.result?.categories != null)
        assert(successResponse.result?.categories!!.items.isNotEmpty())
    }

    @Test
    fun `when categories spotify api responds error then failure is received`() = runTest {
        MockKtorService.isSuccess = false

        val errorResponse = browseApi.getCategories(locale = "en_US", limit = 20, offset = 0)

        assert(errorResponse.result == null)
        assert(errorResponse.failure?.error?.status == 500)
    }

    private fun createMockConfig() = mapOf(
        "available-genre-seeds" to MockResponse(
            expectedSuccessResponsePath = "browse/genres_success.json",
            expectedErrorResponsePath = "error.json"
        ),
        "browse/categories" to MockResponse(
            expectedSuccessResponsePath = "browse/categories_success.json",
            expectedErrorResponsePath = "error.json"
        )
    )
}