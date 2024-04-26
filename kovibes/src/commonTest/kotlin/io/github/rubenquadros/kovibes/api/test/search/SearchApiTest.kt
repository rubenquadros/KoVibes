package io.github.rubenquadros.kovibes.api.test.search

import io.github.rubenquadros.kovibes.api.search.SearchApiImpl
import io.github.rubenquadros.kovibes.api.test.MockKtorService
import io.github.rubenquadros.kovibes.api.test.MockResponse
import io.github.rubenquadros.kovibes.api.test.assertApiResponseFailure
import io.github.rubenquadros.kovibes.api.test.assertApiResponseSuccess
import io.github.rubenquadros.kovibes.api.test.errorResponsePath
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

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
            { it.tracks.items.isNotEmpty() }
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
            { it.artists.items.isNotEmpty() }
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
            { it.albumResponse.items.isNotEmpty() }
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
            { it.playlists.items.isNotEmpty() }
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