package com.ruben.spotify.api.search

import com.ruben.spotify.api.mapper.toAlbum
import com.ruben.spotify.api.mapper.toArtist
import com.ruben.spotify.api.mapper.toPlayList
import com.ruben.spotify.api.mapper.toTrack
import com.ruben.spotify.api.models.AlbumInfo
import com.ruben.spotify.api.models.ArtistInfo
import com.ruben.spotify.api.models.PlaylistInfo
import com.ruben.spotify.api.models.TrackInfo
import com.ruben.spotify.api.response.Albums
import com.ruben.spotify.api.response.Artists
import com.ruben.spotify.api.response.Playlists
import com.ruben.spotify.api.response.Tracks
import com.ruben.spotify.api.search.models.SearchAlbumResponse
import com.ruben.spotify.api.search.models.SearchArtistResponse
import com.ruben.spotify.api.search.models.SearchPlaylistResponse
import com.ruben.spotify.api.search.models.SearchTrackResponse

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