package com.ruben.spotify.api.test.search

import com.ruben.spotify.api.search.SearchApiImpl
import com.ruben.spotify.api.test.MockKtorService
import com.ruben.spotify.api.test.MockResponse
import com.ruben.spotify.api.test.assertApiResponseFailure
import com.ruben.spotify.api.test.assertApiResponseSuccess
import com.ruben.spotify.api.test.errorResponsePath
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class SearchApiTest {

    private val searchApi = SearchApiImpl(
        ktorService = MockKtorService.createMockKtorService(createMockPlaylistConfig())
    )

    @Test
    fun `when search track spotify api responds success then result is received`() = runTest {
        MockKtorService.isSuccess = true

        val response = searchApi.searchTrack(
            "rap",
            market = null,
            limit = 20,
            offset = 0
        )

        response.assertApiResponseSuccess(
            { response.result!!.tracks.items.isNotEmpty() }
        )
    }

    @Test
    fun `when search track spotify api responds error then failure is received`() = runTest {
        MockKtorService.isSuccess = false

        val response = searchApi.searchTrack(
            "rap",
            market = null,
            limit = 20,
            offset = 0
        )

        response.assertApiResponseFailure()
    }

    @Test
    fun `when search artists spotify api responds success then result is received`() = runTest {
        MockKtorService.isSuccess = true

        val response = searchApi.searchArtist(
            "rap",
            market = null,
            limit = 20,
            offset = 0
        )

        response.assertApiResponseSuccess(
            { response.result!!.artists.items.isNotEmpty() }
        )
    }

    @Test
    fun `when search artists spotify api responds error then failure is received`() = runTest {
        MockKtorService.isSuccess = false

        val response = searchApi.searchArtist(
            "rap",
            market = null,
            limit = 20,
            offset = 0
        )

        response.assertApiResponseFailure()
    }

    @Test
    fun `when search albums spotify api responds success then result is received`() = runTest {
        MockKtorService.isSuccess = true

        val response = searchApi.searchAlbum(
            "rap",
            market = null,
            limit = 20,
            offset = 0
        )

        response.assertApiResponseSuccess(
            { response.result!!.albums.items.isNotEmpty() }
        )
    }

    @Test
    fun `when search albums spotify api responds error then failure is received`() = runTest {
        MockKtorService.isSuccess = false

        val response = searchApi.searchAlbum(
            "rap",
            market = null,
            limit = 20,
            offset = 0
        )

        response.assertApiResponseFailure()
    }

    @Test
    fun `when search playlists spotify api responds success then result is received`() = runTest {
        MockKtorService.isSuccess = true

        val response = searchApi.searchPlaylist(
            "rap",
            market = null,
            limit = 20,
            offset = 0
        )

        response.assertApiResponseSuccess(
            { response.result!!.playlists.items.isNotEmpty() }
        )
    }

    @Test
    fun `when search playlists spotify api responds error then failure is received`() = runTest {
        MockKtorService.isSuccess = false

        val response = searchApi.searchPlaylist(
            "rap",
            market = null,
            limit = 20,
            offset = 0
        )

        response.assertApiResponseFailure()
    }

    private fun createMockPlaylistConfig() = mapOf(
        "type=track" to MockResponse(
            expectedSuccessResponsePath = "search/tracks.json",
            expectedErrorResponsePath = errorResponsePath
        ),
        "type=artist" to MockResponse(
            expectedSuccessResponsePath = "search/artists.json",
            expectedErrorResponsePath = errorResponsePath
        ),
        "type=album" to MockResponse(
            expectedSuccessResponsePath = "search/albums.json",
            expectedErrorResponsePath = errorResponsePath
        ),
        "type=playlist" to MockResponse(
            expectedSuccessResponsePath = "search/playlists.json",
            expectedErrorResponsePath = errorResponsePath
        )
    )
}