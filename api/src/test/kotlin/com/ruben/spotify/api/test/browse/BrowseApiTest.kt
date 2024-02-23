package com.ruben.spotify.api.test.browse

import com.ruben.spotify.api.browse.BrowseApiImpl
import com.ruben.spotify.api.test.MockKtorService
import com.ruben.spotify.api.test.MockResponse
import com.ruben.spotify.api.test.assertApiResponseFailure
import com.ruben.spotify.api.test.assertApiResponseSuccess
import com.ruben.spotify.api.test.errorResponsePath
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class BrowseApiTest {

    private val browseApi = BrowseApiImpl(
        ktorService = MockKtorService.createMockKtorService(createMockBrowseConfig())
    )

    @Test
    fun `when genres spotify api responds success then result is received`() = runTest {
        MockKtorService.isSuccess = true

        val successResponse = browseApi.getGenres()

        successResponse.assertApiResponseSuccess(
            { successResponse.result?.genres?.isNotEmpty() == true }
        )
    }

    @Test
    fun `when genres spotify api responds error then failure is received`() = runTest {
        MockKtorService.isSuccess = false

        val errorResponse = browseApi.getGenres()

        errorResponse.assertApiResponseFailure()
    }

    @Test
    fun `when categories spotify api responds success then result is received`() = runTest {
        MockKtorService.isSuccess = true

        val successResponse = browseApi.getCategories(locale = "en_US", limit = 20, offset = 0)

        successResponse.assertApiResponseSuccess(
            { successResponse.result?.categories?.items?.isNotEmpty() == true }
        )
    }

    @Test
    fun `when categories spotify api responds error then failure is received`() = runTest {
        MockKtorService.isSuccess = false

        val errorResponse = browseApi.getCategories(locale = "en_US", limit = 20, offset = 0)

        errorResponse.assertApiResponseFailure()
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