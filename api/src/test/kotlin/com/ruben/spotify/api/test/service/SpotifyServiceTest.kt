package com.ruben.spotify.api.test.service

import com.ruben.spotify.api.SpotifyServiceImpl
import com.ruben.spotify.api.test.assertSpotifyApiError
import com.ruben.spotify.api.test.assertSpotifyApiSuccess
import com.ruben.spotify.api.test.getSuccessSpotifyApiResponse
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class SpotifyServiceTest {

    private val spotifyService = SpotifyServiceImpl(
        FakePlaylistApi(),
        FakeBrowseApi()
    )

    @Test
    fun `when get featured playlist responds success then response is received`() = runTest {
        FakePlaylistApi.isSuccess = true

        val response = spotifyService.getFeaturedPlaylists()

        response.assertSpotifyApiSuccess(
            { response.getSuccessSpotifyApiResponse().items.isNotEmpty() }
        )
    }

    @Test
    fun `when get featured playlist responds error then error is received`() = runTest {
        FakePlaylistApi.isSuccess = false

        val response = spotifyService.getFeaturedPlaylists()

        response.assertSpotifyApiError()
    }

    @Test
    fun `when get playlist tracks responds success then response is received`() = runTest {
        FakePlaylistApi.isSuccess = true

        val response = spotifyService.getPlaylistTracks("123")

        response.assertSpotifyApiSuccess(
            { response.getSuccessSpotifyApiResponse().tracks.isNotEmpty() }
        )
    }

    @Test
    fun `when get playlist tracks responds error then error is received`() = runTest {
        FakePlaylistApi.isSuccess = false

        val response = spotifyService.getPlaylistTracks("123")

        response.assertSpotifyApiError()
    }

    @Test
    fun `when get genres responds success then response is received`() = runTest {
        FakeBrowseApi.isSuccess = true

        val response = spotifyService.getGenres()

        response.assertSpotifyApiSuccess(
            { response.getSuccessSpotifyApiResponse().genres.isNotEmpty() }
        )
    }

    @Test
    fun `when get genres responds error then error is received`() = runTest {
        FakeBrowseApi.isSuccess = false

        val response = spotifyService.getGenres()

        response.assertSpotifyApiError()
    }

    @Test
    fun `when get categories responds success then response is received`() = runTest {
        FakeBrowseApi.isSuccess = true

        val response = spotifyService.getCategories()

        response.assertSpotifyApiSuccess(
            { response.getSuccessSpotifyApiResponse().items.isNotEmpty() }
        )
    }

    @Test
    fun `when get categories responds error then error is received`() = runTest {
        FakeBrowseApi.isSuccess = false

        val response = spotifyService.getCategories()

        response.assertSpotifyApiError()
    }
}