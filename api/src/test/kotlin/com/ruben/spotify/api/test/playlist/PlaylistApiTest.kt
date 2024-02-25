package com.ruben.spotify.api.test.playlist

import com.ruben.spotify.api.playlist.PlaylistApiImpl
import com.ruben.spotify.api.test.MockKtorService
import com.ruben.spotify.api.test.MockResponse
import com.ruben.spotify.api.test.assertApiResponseFailure
import com.ruben.spotify.api.test.assertApiResponseSuccess
import com.ruben.spotify.api.test.errorResponsePath
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

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
            { response.result!!.playlists.items.isNotEmpty() }
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
    fun `when playlist tracks spotify api responds success then result is received`() = runTest {
        MockKtorService.isSuccess = true

        val response = playlistApi.getPlaylistTracks(
            id = "123",
            market = null,
            fields = null,
            limit = 20,
            offset = 0
        )

        response.assertApiResponseSuccess(
            { response.result!!.items.isNotEmpty() }
        )
    }

    @Test
    fun `when playlist tracks spotify api responds error then failure is received`() = runTest {
        MockKtorService.isSuccess = false

        val response = playlistApi.getPlaylistTracks(
            id = "123",
            market = null,
            fields = null,
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
        "playlists/123/tracks" to MockResponse(
            expectedSuccessResponsePath = "playlist/playlist_tracks.json",
            expectedErrorResponsePath = errorResponsePath
        )
    )
}