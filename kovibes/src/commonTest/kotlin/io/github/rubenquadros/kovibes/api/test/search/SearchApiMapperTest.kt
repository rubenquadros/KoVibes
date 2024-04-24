package io.github.rubenquadros.kovibes.api.test.search

import io.github.rubenquadros.kovibes.api.search.models.SearchArtistResponse
import io.github.rubenquadros.kovibes.api.search.models.SearchPlaylistResponse
import io.github.rubenquadros.kovibes.api.search.models.SearchTrackResponse
import io.github.rubenquadros.kovibes.api.search.toSearchArtist
import io.github.rubenquadros.kovibes.api.search.toSearchPlaylist
import io.github.rubenquadros.kovibes.api.search.toSearchTrack
import io.github.rubenquadros.kovibes.api.test.getExpectedResponse
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.assertTrue

class SearchApiMapperTest {

    @Test
    fun `search tracks response is mapped to tracks`() {
        val searchTracksResponseJson = Json.decodeFromString<SearchTrackResponse>(
            getExpectedResponse("search/tracks.json")
        )

        val searchTracks = searchTracksResponseJson.toSearchTrack()

        assertTrue {
            searchTracksResponseJson.tracks.items.size == searchTracks.items.size
        }
    }

    @Test
    fun `search artists response is mapped to artists`() {
        val searchArtistsResponseJson = Json.decodeFromString<SearchArtistResponse>(
            getExpectedResponse("search/artists.json")
        )

        val searchArtists = searchArtistsResponseJson.toSearchArtist()

        assertTrue {
            searchArtistsResponseJson.artists.items.size == searchArtists.items.size
        }
    }

    @Test
    fun `search playlists response is mapped to playlists`() {
        val searchPlaylistsResponseJson = Json.decodeFromString<SearchPlaylistResponse>(
            getExpectedResponse("search/playlists.json")
        )

        val searchAlbums = searchPlaylistsResponseJson.toSearchPlaylist()

        assertTrue {
            searchPlaylistsResponseJson.playlists.items.size == searchAlbums.items.size
        }
    }
}