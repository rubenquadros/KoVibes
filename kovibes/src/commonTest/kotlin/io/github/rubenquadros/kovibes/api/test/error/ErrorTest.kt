package io.github.rubenquadros.kovibes.api.test.error

import io.github.rubenquadros.kovibes.api.browse.BrowseApiImpl
import io.github.rubenquadros.kovibes.api.test.MockKtorService
import io.github.rubenquadros.kovibes.api.test.MockResponse
import io.github.rubenquadros.kovibes.api.test.assertApiResponseFailure
import io.github.rubenquadros.kovibes.api.test.errorResponsePath
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ErrorTest {

    private val browseApi = BrowseApiImpl(
        ktorService = MockKtorService.createMockKtorService(createMockBrowseConfig())
    )

    @Test
    fun `when there is a parsing exception then error is received`() = runTest {
        MockKtorService.isSuccess = true

        val response = browseApi.getCategories(locale = "en_US", limit = 20, offset = 0)

        response.assertApiResponseFailure()
    }

    private fun createMockBrowseConfig() = mapOf(
        "browse/categories" to MockResponse(
            expectedSuccessResponsePath = "invalid.json",
            expectedErrorResponsePath = errorResponsePath
        )
    )
}