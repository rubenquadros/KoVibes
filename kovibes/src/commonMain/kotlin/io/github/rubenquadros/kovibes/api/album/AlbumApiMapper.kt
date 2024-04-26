package io.github.rubenquadros.kovibes.api.album

import io.github.rubenquadros.kovibes.api.album.models.GetAlbumTracksResponse
import io.github.rubenquadros.kovibes.api.album.models.GetNewAlbumReleasesResponse
import io.github.rubenquadros.kovibes.api.album.models.SimplifiedTrackInfo
import io.github.rubenquadros.kovibes.api.mapper.toAlbum
import io.github.rubenquadros.kovibes.api.mapper.toArtist
import io.github.rubenquadros.kovibes.api.response.AlbumTrack
import io.github.rubenquadros.kovibes.api.response.AlbumTracks
import io.github.rubenquadros.kovibes.api.response.NewAlbumReleases

/**
 * @suppress
 * Map [GetAlbumTracksResponse] to [AlbumTracks].
 */
internal fun GetAlbumTracksResponse.toAlbumTracks(): AlbumTracks {
    return AlbumTracks(
        isNext = next != null,
        items = items.map { it.toAlbumTrack() }
    )
}

/**
 * @suppress
 * Map [GetNewAlbumReleasesResponse] to [NewAlbumReleases].
 */
internal fun GetNewAlbumReleasesResponse.toNewAlbumReleases(): NewAlbumReleases {
    return NewAlbumReleases(
        isNext = albums.next != null,
        items = albums.items.map { it.toAlbum() }
    )
}

private fun SimplifiedTrackInfo.toAlbumTrack(): AlbumTrack {
    return AlbumTrack(
        id = id,
        name = name,
        previewUrl = previewUrl,
        duration = duration,
        artists = artists.map { it.toArtist() }
    )
}