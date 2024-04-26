package io.github.rubenquadros.kovibes.api.test.album

import io.github.rubenquadros.kovibes.api.album.models.GetAlbumTracksResponse
import io.github.rubenquadros.kovibes.api.album.models.GetNewAlbumReleasesResponse
import io.github.rubenquadros.kovibes.api.album.toAlbumTracks
import io.github.rubenquadros.kovibes.api.album.toNewAlbumReleases
import io.github.rubenquadros.kovibes.api.test.getExpectedResponse
import io.github.rubenquadros.kovibes.api.test.json
import kotlin.test.Test
import kotlin.test.assertTrue

class AlbumApiMapperTest {

    @Test
    fun `get album tracks response is mapped to album tracks`() {
        val albumTracksResponse = json.decodeFromString<GetAlbumTracksResponse>(
            getExpectedResponse("album/tracks.json")
        )

        val albumTracks = albumTracksResponse.toAlbumTracks()

        assertTrue {
            albumTracksResponse.items.size == albumTracks.items.size
        }
    }

    @Test
    fun `new album releases response is mapped to new album releases`() {
        val newAlbumReleasesResponse = json.decodeFromString<GetNewAlbumReleasesResponse>(
            getExpectedResponse("album/new_releases.json")
        )

        val newAlbumReleases = newAlbumReleasesResponse.toNewAlbumReleases()

        assertTrue {
            newAlbumReleasesResponse.albums.items.size == newAlbumReleases.items.size
        }
    }
}