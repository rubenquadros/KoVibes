package io.github.rubenquadros.kovibes.api.test.artist

import io.github.rubenquadros.kovibes.api.artist.ArtistApiImpl
import io.github.rubenquadros.kovibes.api.test.MockKtorService
import io.github.rubenquadros.kovibes.api.test.MockResponse
import io.github.rubenquadros.kovibes.api.test.assertApiResponseFailure
import io.github.rubenquadros.kovibes.api.test.assertApiResponseSuccess
import io.github.rubenquadros.kovibes.api.test.errorResponsePath
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class ArtistApiTest {

    private val artistApi = ArtistApiImpl(
        ktorService = MockKtorService.createMockKtorService(createMockArtistConfig())
    )

    @Test
    fun `when artist spotify api responds success then result is received`() = runTest {
        MockKtorService.isSuccess = true

        val response = artistApi.getArtist("123")

        response.assertApiResponseSuccess(
            { it.genres?.isNotEmpty() == true }
        )
    }

    @Test
    fun `when artist spotify api responds error then failure is received`() = runTest {
        MockKtorService.isSuccess = false

        val response = artistApi.getArtist("123")

        response.assertApiResponseFailure()
    }

    @Test
    fun `when artist albums spotify api responds success then result is received`() = runTest {
        MockKtorService.isSuccess = true

        val response = artistApi.getArtistAlbums(
            id = "456", offset = 0, limit = 20, market = null, includeGroups = listOf("album")
        )

        response.assertApiResponseSuccess(
            { it.items.isNotEmpty() }
        )
    }

    @Test
    fun `when artist albums spotify api responds error then failure is received`() = runTest {
        MockKtorService.isSuccess = false

        val response = artistApi.getArtistAlbums(
            id = "456", offset = 0, limit = 20, market = null, includeGroups = listOf("album")
        )

        response.assertApiResponseFailure()
    }

    @Test
    fun `when artist top tracks spotify api responds success then result is received`() = runTest {
        MockKtorService.isSuccess = true

        val response = artistApi.getArtistTopTracks(id = "678", market = null)

        response.assertApiResponseSuccess(
            { it.tracks.isNotEmpty() }
        )
    }

    @Test
    fun `when artist top tracks spotify api responds error then failure is received`() = runTest {
        MockKtorService.isSuccess = false

        val response = artistApi.getArtistTopTracks(id = "678", market = null)

        response.assertApiResponseFailure()
    }

    private fun createMockArtistConfig() = mapOf(
        "artists/123" to MockResponse(
            expectedSuccessResponsePath = "artist/artist.json",
            expectedErrorResponsePath = errorResponsePath
        ),
        "artists/456/albums" to MockResponse(
            expectedSuccessResponsePath = "artist/albums.json",
            expectedErrorResponsePath = errorResponsePath
        ),
        "artists/678/top-tracks" to MockResponse(
            expectedSuccessResponsePath = "artist/top_tracks.json",
            expectedErrorResponsePath = errorResponsePath
        )
    )
}