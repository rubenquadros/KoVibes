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

        val successResponse = playlistApi.getFeaturedPlaylists(
            locale = "en_US",
            limit = 20,
            offset = 0
        )

        successResponse.assertApiResponseSuccess(
            { successResponse.result?.playlists?.items?.isNotEmpty() == true }
        )
    }

    @Test
    fun `when featured playlist spotify api responds error then failure is received`() = runTest {
        MockKtorService.isSuccess = false

        val errorResponse = playlistApi.getFeaturedPlaylists(
            locale = "en_US",
            limit = 20,
            offset = 0
        )

        errorResponse.assertApiResponseFailure()
    }

    @Test
    fun `when playlist tracks spotify api responds success then result is received`() = runTest {
        MockKtorService.isSuccess = true

        val successResponse = playlistApi.getPlaylistTracks(
            id = "123",
            market = null,
            fields = null,
            limit = 20,
            offset = 0
        )

        successResponse.assertApiResponseSuccess(
            { successResponse.result?.items?.isNotEmpty() == true }
        )
    }

    @Test
    fun `when playlist tracks spotify api responds error then failure is received`() = runTest {
        MockKtorService.isSuccess = false

        val errorResponse = playlistApi.getPlaylistTracks(
            id = "123",
            market = null,
            fields = null,
            limit = 20,
            offset = 0
        )

        errorResponse.assertApiResponseFailure()
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