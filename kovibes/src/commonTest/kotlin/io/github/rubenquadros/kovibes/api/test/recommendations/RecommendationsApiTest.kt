package io.github.rubenquadros.kovibes.api.test.recommendations

import io.github.rubenquadros.kovibes.api.ApiResponse
import io.github.rubenquadros.kovibes.api.recommendations.RecommendationsApiImpl
import io.github.rubenquadros.kovibes.api.recommendations.models.RecommendationsResponse
import io.github.rubenquadros.kovibes.api.request.GetRecommendationsRequest
import io.github.rubenquadros.kovibes.api.response.ErrorBody
import io.github.rubenquadros.kovibes.api.test.MockKtorService
import io.github.rubenquadros.kovibes.api.test.MockResponse
import io.github.rubenquadros.kovibes.api.test.assertApiResponseFailure
import io.github.rubenquadros.kovibes.api.test.assertApiResponseSuccess
import io.github.rubenquadros.kovibes.api.test.errorResponsePath
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class RecommendationsApiTest {

    private val recommendationsApi = RecommendationsApiImpl(
        ktorService = MockKtorService.createMockKtorService(createMockPlaylistConfig())
    )

    private val testRequest = GetRecommendationsRequest(seedTracks = listOf("1234"))

    @Test
    fun `when neither genres, tracks and artists are not provided then failure is received`() = runTest {
        MockKtorService.isSuccess = true

        val response = recommendationsApi.getRecommendations(GetRecommendationsRequest())

        response.assertRequestValidationFailed()
    }

    @Test
    fun `when only tracks are provided then result is received`() = runTest {
        MockKtorService.isSuccess = true

        val response = recommendationsApi.getRecommendations(
            GetRecommendationsRequest(seedTracks = listOf("1234"))
        )

        response.assertApiResponseSuccess(
            { it.tracks.isNotEmpty() }
        )
    }

    @Test
    fun `when only genres are provided then result is received`() = runTest {
        MockKtorService.isSuccess = true

        val response = recommendationsApi.getRecommendations(
            GetRecommendationsRequest(seedGenres = listOf("1234"))
        )

        response.assertApiResponseSuccess(
            { it.tracks.isNotEmpty() }
        )

    }

    @Test
    fun `when only artists are provided then result is received`() = runTest {
        MockKtorService.isSuccess = true

        val response = recommendationsApi.getRecommendations(
            GetRecommendationsRequest(seedArtists = listOf("1234"))
        )

        response.assertApiResponseSuccess(
            { it.tracks.isNotEmpty() }
        )
    }

    @Test
    fun `when recommendations spotify api responds success then result is received`() = runTest {
        MockKtorService.isSuccess = true

        val response = recommendationsApi.getRecommendations(testRequest)

        response.assertApiResponseSuccess(
            { it.tracks.isNotEmpty() }
        )

    }

    @Test
    fun `when recommendations spotify api responds error then failure is received`() = runTest {
        MockKtorService.isSuccess = false

        val response = recommendationsApi.getRecommendations(testRequest)

        response.assertApiResponseFailure()
    }

    private fun createMockPlaylistConfig() = mapOf(
        "v1/recommendations" to MockResponse(
            expectedSuccessResponsePath = "recommendations/recommendations.json",
            expectedErrorResponsePath = errorResponsePath
        ),
    )

    private fun ApiResponse<RecommendationsResponse, ErrorBody>.assertRequestValidationFailed() {
        assert(result == null)

        assert(failure != null)

        assert(failure!!.error.status == 400)

        assert(
            failure.error.message == "Invalid request - Please provide one of seedArtists, seedGenres or seedTracks."
        )
    }
}