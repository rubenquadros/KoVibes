package io.github.rubenquadros.kovibes.api.test.artist

import io.github.rubenquadros.kovibes.api.artist.models.GetArtistTopTracksResponse
import io.github.rubenquadros.kovibes.api.artist.models.GetRelatedArtistsResponse
import io.github.rubenquadros.kovibes.api.artist.toArtistTopTracks
import io.github.rubenquadros.kovibes.api.artist.toRelatedArtists
import io.github.rubenquadros.kovibes.api.test.getExpectedResponse
import io.github.rubenquadros.kovibes.api.test.json
import kotlin.test.Test
import kotlin.test.assertTrue

class ArtistApiMapperTest {

    @Test
    fun `artist top tracks response is mapped to artist top tracks`() {
        val artistTopTracksResponse = json.decodeFromString<GetArtistTopTracksResponse>(
            getExpectedResponse("artist/top_tracks.json")
        )

        val artistTopTracks = artistTopTracksResponse.toArtistTopTracks()

        assertTrue {
            artistTopTracksResponse.tracks.size == artistTopTracks.tracks.size
        }
    }

    @Test
    fun `related artists response is mapped to related artists`() {
        val relatedArtistsResponse = json.decodeFromString<GetRelatedArtistsResponse>(
            getExpectedResponse("artist/related_artists.json")
        )

        val relatedArtists = relatedArtistsResponse.toRelatedArtists()

        assertTrue {
            relatedArtistsResponse.artists.size == relatedArtists.artists.size
        }
    }
}