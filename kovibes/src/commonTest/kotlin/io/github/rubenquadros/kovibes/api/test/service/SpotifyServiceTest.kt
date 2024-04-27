package io.github.rubenquadros.kovibes.api.test.service

import io.github.rubenquadros.kovibes.api.SpotifyServiceImpl
import io.github.rubenquadros.kovibes.api.request.GetRecommendationsRequest
import io.github.rubenquadros.kovibes.api.test.assertSpotifyApiError
import io.github.rubenquadros.kovibes.api.test.assertSpotifyApiSuccess
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class SpotifyServiceTest {

    private val spotifyService = SpotifyServiceImpl(
        FakePlaylistApi(),
        FakeBrowseApi(),
        FakeSearchApi(),
        FakeRecommendationsApi(),
        FakeArtistApi(),
        FakeAlbumApi()
    )

    @Test
    fun `when get featured playlist responds success then response is received`() = runTest {
        FakePlaylistApi.isSuccess = true

        val response = spotifyService.getFeaturedPlaylists()

        response.assertSpotifyApiSuccess(
            { it.items.isNotEmpty() },
            { it.isNext }
        )
    }

    @Test
    fun `when get featured playlist responds error then error is received`() = runTest {
        FakePlaylistApi.isSuccess = false

        val response = spotifyService.getFeaturedPlaylists()

        response.assertSpotifyApiError()
    }

    @Test
    fun `when get playlist info responds success then response is received`() = runTest {
        FakePlaylistApi.isSuccess = true

        val response = spotifyService.getPlaylist("123")

        response.assertSpotifyApiSuccess(
            { it.id == "37i9dQZF1DXdGUQjVlqY2Q" }
        )
    }

    @Test
    fun `when get playlist info responds error then error is received`() = runTest {
        FakePlaylistApi.isSuccess = false

        val response = spotifyService.getPlaylist("123")

        response.assertSpotifyApiError()
    }

    @Test
    fun `when get playlist tracks responds success then response is received`() = runTest {
        FakePlaylistApi.isSuccess = true

        val response = spotifyService.getPlaylistTracks("123")

        response.assertSpotifyApiSuccess(
            { it.tracks.isNotEmpty() },
            { it.isNext.not() }
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
            { it.genres.isNotEmpty() }
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
            { it.items.isNotEmpty() },
            { it.isNext }
        )
    }

    @Test
    fun `when get categories responds error then error is received`() = runTest {
        FakeBrowseApi.isSuccess = false

        val response = spotifyService.getCategories()

        response.assertSpotifyApiError()
    }

    @Test
    fun `when search tracks responds success then response is received`() = runTest {
        FakeSearchApi.isSuccess = true

        val response = spotifyService.searchTrack("rap")

        response.assertSpotifyApiSuccess(
            { it.items.isNotEmpty() },
            { it.isNext }
        )
    }

    @Test
    fun `when search tracks responds error then error is received`() = runTest {
        FakeSearchApi.isSuccess = false

        val response = spotifyService.searchTrack("rap")

        response.assertSpotifyApiError()
    }

    @Test
    fun `when search artists responds success then response is received`() = runTest {
        FakeSearchApi.isSuccess = true

        val response = spotifyService.searchArtist("rap")

        response.assertSpotifyApiSuccess(
            { it.items.isNotEmpty() },
            { it.isNext }
        )
    }

    @Test
    fun `when search artists responds error then error is received`() = runTest {
        FakeSearchApi.isSuccess = false

        val response = spotifyService.searchArtist("rap")

        response.assertSpotifyApiError()
    }

    @Test
    fun `when search albums responds success then response is received`() = runTest {
        FakeSearchApi.isSuccess = true

        val response = spotifyService.searchAlbum("rap")

        response.assertSpotifyApiSuccess(
            { it.items.isNotEmpty() },
            { it.isNext }
        )
    }

    @Test
    fun `when search albums responds error then error is received`() = runTest {
        FakeSearchApi.isSuccess = false

        val response = spotifyService.searchAlbum("rap")

        response.assertSpotifyApiError()
    }

    @Test
    fun `when search playlists responds success then response is received`() = runTest {
        FakeSearchApi.isSuccess = true

        val response = spotifyService.searchPlaylist("rap")

        response.assertSpotifyApiSuccess(
            { it.items.isNotEmpty() },
            { it.isNext }
        )
    }

    @Test
    fun `when search playlists responds error then error is received`() = runTest {
        FakeSearchApi.isSuccess = false

        val response = spotifyService.searchPlaylist("rap")

        response.assertSpotifyApiError()
    }

    @Test
    fun `when get recommendations responds success then response is received`() = runTest {
        FakeRecommendationsApi.isSuccess = true

        val response =
            spotifyService.getRecommendations(GetRecommendationsRequest(seedArtists = listOf("1234")))

        response.assertSpotifyApiSuccess(
            { it.tracks.isNotEmpty() }
        )
    }

    @Test
    fun `when get recommendations responds error then error is received`() = runTest {
        FakeRecommendationsApi.isSuccess = false

        val response =
            spotifyService.getRecommendations(GetRecommendationsRequest(seedTracks = listOf("1234")))

        response.assertSpotifyApiError()
    }

    @Test
    fun `when get artist responds success then response is received`() = runTest {
        FakeArtistApi.isSuccess = true

        val response = spotifyService.getArtist(id = "123")

        response.assertSpotifyApiSuccess(
            { it.id == "06HL4z0CvFAxyc27GXpf02" }
        )
    }

    @Test
    fun `when get artist responds error then error is received`() = runTest {
        FakeArtistApi.isSuccess = false

        val response = spotifyService.getArtist(id = "123")

        response.assertSpotifyApiError()
    }

    @Test
    fun `when get artist albums responds success then response is received`() = runTest {
        FakeArtistApi.isSuccess = true

        val response = spotifyService.getArtistAlbums(id = "456")

        response.assertSpotifyApiSuccess(
            { it.items.isNotEmpty() },
            { it.isNext }
        )
    }

    @Test
    fun `when get artist albums responds error then error is received`() = runTest {
        FakeArtistApi.isSuccess = false

        val response = spotifyService.getArtistAlbums(id = "456")

        response.assertSpotifyApiError()
    }

    @Test
    fun `when get artist top tracks responds success then response is received`() = runTest {
        FakeArtistApi.isSuccess = true

        val response = spotifyService.getArtistTopTracks(id = "678")

        response.assertSpotifyApiSuccess(
            { it.tracks.isNotEmpty() }
        )
    }

    @Test
    fun `when get artist top tracks responds error then error is received`() = runTest {
        FakeArtistApi.isSuccess = false

        val response = spotifyService.getArtistTopTracks(id = "678")

        response.assertSpotifyApiError()
    }

    @Test
    fun `when get related artists responds success then response is received`() = runTest {
        FakeArtistApi.isSuccess = true

        val response = spotifyService.getRelatedArtists(id = "567")

        response.assertSpotifyApiSuccess(
            { it.artists.isNotEmpty() }
        )
    }

    @Test
    fun `when get related artists responds error then error is received`() = runTest {
        FakeArtistApi.isSuccess = false

        val response = spotifyService.getRelatedArtists(id = "567")

        response.assertSpotifyApiError()
    }

    @Test
    fun `when get album responds success then response is received`() = runTest {
        FakeAlbumApi.isSuccess = true

        val response = spotifyService.getAlbum(id = "123")

        response.assertSpotifyApiSuccess(
            { it.artists.isNotEmpty() }
        )
    }

    @Test
    fun `when get album responds error then error is received`() = runTest {
        FakeAlbumApi.isSuccess = false

        val response = spotifyService.getAlbum(id = "123")

        response.assertSpotifyApiError()
    }

    @Test
    fun `when get album tracks responds success then response is received`() = runTest {
        FakeAlbumApi.isSuccess = true

        val response = spotifyService.getAlbumTracks(id = "123")

        response.assertSpotifyApiSuccess(
            { it.items.isNotEmpty() }
        )
    }

    @Test
    fun `when get album tracks responds error then error is received`() = runTest {
        FakeAlbumApi.isSuccess = false

        val response = spotifyService.getAlbumTracks(id = "123")

        response.assertSpotifyApiError()
    }

    @Test
    fun `when get new album releases responds success ten response is received`() = runTest {
        FakeAlbumApi.isSuccess = true

        val response = spotifyService.getNewAlbumReleases()

        response.assertSpotifyApiSuccess(
            { it.items.isNotEmpty() }
        )
    }

    @Test
    fun `when get new album releases responds error then error is received`() = runTest {
        FakeAlbumApi.isSuccess = false

        val response = spotifyService.getNewAlbumReleases()

        response.assertSpotifyApiError()
    }
}