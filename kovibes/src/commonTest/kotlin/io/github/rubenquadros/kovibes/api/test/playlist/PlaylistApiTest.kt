package io.github.rubenquadros.kovibes.api.test.playlist

import io.github.rubenquadros.kovibes.api.playlist.PlaylistApiImpl
import io.github.rubenquadros.kovibes.api.test.MockKtorService
import io.github.rubenquadros.kovibes.api.test.MockResponse
import io.github.rubenquadros.kovibes.api.test.assertApiResponseFailure
import io.github.rubenquadros.kovibes.api.test.assertApiResponseSuccess
import io.github.rubenquadros.kovibes.api.test.errorResponsePath
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class PlaylistApiTest {

    private val playlistApi = PlaylistApiImpl(
        ktorService = MockKtorService.createMockKtorService(createMockPlaylistConfig())
    )

    @Test
    fun `when featured playlist spotify api responds success then result is received`() = runTest {
        MockKtorService.isSuccess = true

        val response = playlistApi.getFeaturedPlaylists(
            locale = "en_US",
            limit = 20,
            offset = 0
        )

        response.assertApiResponseSuccess(
            { it.playlists.items.isNotEmpty() }
        )
    }

    @Test
    fun `when featured playlist spotify api responds error then failure is received`() = runTest {
        MockKtorService.isSuccess = false

        val response = playlistApi.getFeaturedPlaylists(
            locale = "en_US",
            limit = 20,
            offset = 0
        )

        response.assertApiResponseFailure()
    }

    @Test
    fun `when playlist info spotify api responds success then result is received`() = runTest {
        MockKtorService.isSuccess = true

        val response = playlistApi.getPlaylist(
            id = "456",
            market = "US",
            fields = listOf("description"),
            additionalTypes = listOf("track")
        )

        response.assertApiResponseSuccess(
            { it.id == "37i9dQZF1DXdGUQjVlqY2Q" }
        )
    }

    @Test
    fun `when playlist info spotify api responds error then failure is received`() = runTest {
        MockKtorService.isSuccess = false

        val response = playlistApi.getPlaylist(
            id = "456",
            market = null,
            fields = null,
            additionalTypes = listOf("track")
        )

        response.assertApiResponseFailure()
    }

    @Test
    fun `when playlist tracks spotify api responds success then result is received`() = runTest {
        MockKtorService.isSuccess = true

        val response = playlistApi.getPlaylistTracks(
            id = "123",
            market = "US",
            fields = listOf("description"),
            additionalTypes = listOf("track"),
            limit = 20,
            offset = 0
        )

        response.assertApiResponseSuccess(
            { it.items.isNotEmpty() }
        )
    }

    @Test
    fun `when playlist tracks spotify api responds error then failure is received`() = runTest {
        MockKtorService.isSuccess = false

        val response = playlistApi.getPlaylistTracks(
            id = "123",
            market = null,
            fields = null,
            additionalTypes = listOf("track"),
            limit = 20,
            offset = 0
        )

        response.assertApiResponseFailure()
    }

    private fun createMockPlaylistConfig() = mapOf(
        "browse/featured-playlists" to MockResponse(
            expectedSuccessResponsePath = "playlist/featured_playlists.json",
            expectedErrorResponsePath = errorResponsePath
        ),
        "playlists/456" to MockResponse(
            expectedSuccessResponsePath = "playlist/playlist.json",
            expectedErrorResponsePath = errorResponsePath
        ),
        "playlists/123/tracks" to MockResponse(
            expectedSuccessResponsePath = "playlist/playlist_tracks.json",
            expectedErrorResponsePath = errorResponsePath
        )
    )
}