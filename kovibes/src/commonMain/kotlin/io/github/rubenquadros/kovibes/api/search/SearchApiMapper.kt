package io.github.rubenquadros.kovibes.api.search

import io.github.rubenquadros.kovibes.api.mapper.toAlbum
import io.github.rubenquadros.kovibes.api.mapper.toArtist
import io.github.rubenquadros.kovibes.api.mapper.toPlayList
import io.github.rubenquadros.kovibes.api.mapper.toTrack
import io.github.rubenquadros.kovibes.api.models.AlbumInfo
import io.github.rubenquadros.kovibes.api.models.ArtistInfo
import io.github.rubenquadros.kovibes.api.models.PlaylistInfo
import io.github.rubenquadros.kovibes.api.models.TrackInfo
import io.github.rubenquadros.kovibes.api.response.Albums
import io.github.rubenquadros.kovibes.api.response.Artists
import io.github.rubenquadros.kovibes.api.response.Playlists
import io.github.rubenquadros.kovibes.api.response.Tracks
import io.github.rubenquadros.kovibes.api.search.models.SearchAlbumResponse
import io.github.rubenquadros.kovibes.api.search.models.SearchArtistResponse
import io.github.rubenquadros.kovibes.api.search.models.SearchPlaylistResponse
import io.github.rubenquadros.kovibes.api.search.models.SearchTrackResponse

/**
 * @suppress
 * Map [SearchPlaylistResponse] to [Playlists].
 */
internal fun SearchPlaylistResponse.toSearchPlaylist(): Playlists {
    return Playlists(
        isNext = this.playlists.next != null,
        items = this.playlists.items.map { item: PlaylistInfo ->
            item.toPlayList()
        }
    )
}

/**
 * @suppress
 * Map [SearchAlbumResponse] to [Albums].
 */
internal fun SearchAlbumResponse.toSearchAlbum(): Albums {
    return Albums(
        isNext = this.albums.next != null,
        items = this.albums.items.map { item: AlbumInfo ->
            item.toAlbum()
        }
    )
}

/**
 * @suppress
 * Map [SearchArtistResponse] to [Artists].
 */
internal fun SearchArtistResponse.toSearchArtist(): Artists {
    return Artists(
        isNext = this.artists.next != null,
        items = this.artists.items.map { item: ArtistInfo ->
            item.toArtist()
        }
    )
}

/**
 * @suppress
 * Map [SearchTrackResponse] to [Tracks].
 */
internal fun SearchTrackResponse.toSearchTrack(): Tracks {
    return Tracks(
        isNext = this.tracks.next != null,
        items = this.tracks.items.map { item: TrackInfo ->
            item.toTrack()
        }
    )
}