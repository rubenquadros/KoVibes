package io.github.rubenquadros.kovibes.api.test.mapper

import io.github.rubenquadros.kovibes.api.mapper.toAlbum
import io.github.rubenquadros.kovibes.api.mapper.toAlbums
import io.github.rubenquadros.kovibes.api.mapper.toArtist
import io.github.rubenquadros.kovibes.api.mapper.toImage
import io.github.rubenquadros.kovibes.api.mapper.toPlayList
import io.github.rubenquadros.kovibes.api.mapper.toRestrictions
import io.github.rubenquadros.kovibes.api.mapper.toTrack
import io.github.rubenquadros.kovibes.api.models.AlbumResponse
import io.github.rubenquadros.kovibes.api.models.ExternalIds
import io.github.rubenquadros.kovibes.api.models.ExternalUrls
import io.github.rubenquadros.kovibes.api.models.PlaylistInfo
import io.github.rubenquadros.kovibes.api.models.PlaylistOwner
import io.github.rubenquadros.kovibes.api.models.PlaylistTracks
import io.github.rubenquadros.kovibes.api.models.TrackInfo
import io.github.rubenquadros.kovibes.api.playlist.models.RestrictionInfo
import io.github.rubenquadros.kovibes.api.test.albumInfo
import io.github.rubenquadros.kovibes.api.test.artistInfo
import io.github.rubenquadros.kovibes.api.test.imageInfo
import kotlin.test.Test
import kotlin.test.assertTrue

class ApiMapperTest {

    @Test
    fun `image info is mapped to image`() {
        val imageInfo = imageInfo

        val image = imageInfo.toImage()

        assertTrue { imageInfo.height == image.height }
    }

    @Test
    fun `restriction info is mapped to restrictions`() {
        val restrictionInfo = RestrictionInfo(
            reason = "Reason for restriction"
        )

        val restrictions = restrictionInfo.toRestrictions()

        assertTrue { restrictionInfo.reason == restrictions.reason }
    }

    @Test
    fun `artist info is mapped to artist`() {
        val artistInfo = artistInfo

        val artist = artistInfo.toArtist()

        assertTrue { artistInfo.id == artist.id }
        assertTrue { artistInfo.name == artist.name }
    }

    @Test
    fun `album info is mapped to album`() {
        val albumInfo = albumInfo

        val album = albumInfo.toAlbum()

        assertTrue { albumInfo.id == album.id }
        assertTrue { albumInfo.name == album.name }
    }

    @Test
    fun `track info is mapped to track`() {
        val trackInfo = TrackInfo(
            albumInfo = albumInfo,
            artistsInfo = listOf(artistInfo),
            discNumber = 1,
            discDuration = 1000,
            isLocal = false,
            isPlayable = true,
            episode = false,
            id = "1234",
            explicit = false,
            name = "Too sweet",
            type = "track",
            trackNumber = 1,
            previewUrl = null,
            href = "https://vibesync.track.href",
            popularity = 80,
            uri = "spotify:track:1u8c2t2Cy7UBoG4ArRcF5g",
            externalUrls = ExternalUrls(spotify = "spotify"),
            externalIds = ExternalIds("1")
        )

        val track = trackInfo.toTrack()

        assertTrue { trackInfo.id == track.id }
        assertTrue { trackInfo.name == track.name }
    }

    @Test
    fun `playlist info is mapped to playlist`() {
        val playlistInfo = PlaylistInfo(
            collaborative = false,
            name = "Awesome playlist",
            id = "4567",
            href = "https://vibesync.playlist.href",
            type = "playlist",
            uri = "spotify:playlist:1u8c2t2Cy7UBoG4ArRcF5g",
            images = listOf(imageInfo),
            description = "This is my awesome playlist",
            owner = PlaylistOwner(
                id = "234",
                type = "spotify",
                externalUrls = ExternalUrls(spotify = "spotify"),
                href = "https://vibesync.owner.href",
                uri = "spotify:owner:1u8c2t2Cy7UBoG4ArRcF5g",
                displayName = "Ruben"
            ),
            externalUrls = ExternalUrls(spotify = "spotify"),
            primaryColor = "#ffffff",
            snapshotId = "12",
            tracks = PlaylistTracks(
                href = "https://vibesync.tracks.href",
                total = 200
            )
        )

        val playlist = playlistInfo.toPlayList()

        assertTrue { playlistInfo.id == playlist.id }
        assertTrue { playlistInfo.name == playlist.name }
    }

    @Test
    fun `album response is mapped to albums`() {
        val albumResponse = AlbumResponse(
            href = "https://vibesync.album.href",
            offset = 0,
            limit = 20,
            total = 500,
            previous = null,
            next = "https://api.spotify.com/v1/artists/06HL4z0CvFAxyc27GXpf02/albums?include_groups=album,single,compilation,appears_on&offset=20&limit=20",
            items = listOf(albumInfo)
        )

        val albums = albumResponse.toAlbums()

        assertTrue { albumResponse.items.size == albums.items.size }
    }
}