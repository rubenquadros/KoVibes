package com.ruben.spotify.api.playlist

import com.ruben.spotify.api.mapper.toPlayList
import com.ruben.spotify.api.mapper.toTrack
import com.ruben.spotify.api.models.PlaylistInfo
import com.ruben.spotify.api.playlist.models.FeaturedPlaylistsResponse
import com.ruben.spotify.api.playlist.models.PlaylistTracksResponse
import com.ruben.spotify.api.playlist.models.TrackItem
import com.ruben.spotify.api.response.PlaylistTracks
import com.ruben.spotify.api.response.Playlists

/**
 * @suppress
 *
 * Map [FeaturedPlaylistsResponse] to [Playlists].
 */
internal fun FeaturedPlaylistsResponse.toFeaturedPlayLists(): Playlists {
    return Playlists(
        items = this.playlists.items.map { item: PlaylistInfo ->
            item.toPlayList()
        },
        isNext = this.playlists.next != null
    )
}

/**
 * @suppress
 *
 * Map [PlaylistTracksResponse] to [PlaylistTracks].
 */
internal fun PlaylistTracksResponse.toPlaylistTracks(): PlaylistTracks {
    return PlaylistTracks(
        tracks = this.items.map { item: TrackItem ->
            item.trackInfo.toTrack(item.addedAt.orEmpty())
        },
        isNext = this.next != null
    )
}