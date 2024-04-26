package io.github.rubenquadros.kovibes.api.test.album

import io.github.rubenquadros.kovibes.api.album.AlbumApiImpl
import io.github.rubenquadros.kovibes.api.test.MockKtorService
import io.github.rubenquadros.kovibes.api.test.MockResponse
import io.github.rubenquadros.kovibes.api.test.assertApiResponseFailure
import io.github.rubenquadros.kovibes.api.test.assertApiResponseSuccess
import io.github.rubenquadros.kovibes.api.test.errorResponsePath
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class AlbumApiTest {

    private val albumApi = AlbumApiImpl(
        ktorService = MockKtorService.createMockKtorService(createMockAlbumConfig())
    )

    @Test
    fun `when album spotify api responds success then result is received`() = runTest {
        MockKtorService.isSuccess = true

        val response = albumApi.getAlbum(id = "123", market = null)

        response.assertApiResponseSuccess(
            { it.artists.isNotEmpty() }
        )
    }

    @Test
    fun `when album spotify api responds error then failure is received`() = runTest {
        MockKtorService.isSuccess = false

        val response = albumApi.getAlbum(id = "123", market = null)

        response.assertApiResponseFailure()
    }

    @Test
    fun `when album tracks spotify api responds success then result is received`() = runTest {
        MockKtorService.isSuccess = true

        val response = albumApi.getAlbumTracks(id = "456", offset = 0, limit = 20, market = null)

        response.assertApiResponseSuccess(
            { it.items.isNotEmpty() }
        )
    }

    @Test
    fun `when album tracks spotify api responds error then failure is received`() = runTest {
        MockKtorService.isSuccess = false

        val response = albumApi.getAlbumTracks(id = "456", offset = 0, limit = 20, market = null)

        response.assertApiResponseFailure()
    }

    @Test
    fun `when new album releases spotify api responds success then result is received`() = runTest {
        MockKtorService.isSuccess = true

        val response = albumApi.getNewAlbumReleases(limit = 20, offset = 0)

        response.assertApiResponseSuccess(
            { it.albums.items.isNotEmpty() }
        )
    }

    @Test
    fun `when new album releases spotify api responds error then failure is received`() = runTest {
        MockKtorService.isSuccess = false

        val response = albumApi.getNewAlbumReleases(limit = 20, offset = 0)

        response.assertApiResponseFailure()
    }

    private fun createMockAlbumConfig() = mapOf(
        "albums/123" to MockResponse(
            expectedSuccessResponsePath = "album/album.json",
            expectedErrorResponsePath = errorResponsePath
        ),
        "albums/456/tracks" to MockResponse(
            expectedSuccessResponsePath = "album/tracks.json",
            expectedErrorResponsePath = errorResponsePath
        ),
        "new-releases" to MockResponse(
            expectedSuccessResponsePath = "album/new_releases.json",
            expectedErrorResponsePath = errorResponsePath
        )
    )
}