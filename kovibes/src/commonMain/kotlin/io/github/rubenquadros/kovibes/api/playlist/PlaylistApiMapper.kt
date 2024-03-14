package io.github.rubenquadros.kovibes.api.playlist

import io.github.rubenquadros.kovibes.api.mapper.toPlayList
import io.github.rubenquadros.kovibes.api.mapper.toTrack
import io.github.rubenquadros.kovibes.api.models.PlaylistInfo
import io.github.rubenquadros.kovibes.api.playlist.models.FeaturedPlaylistsResponse
import io.github.rubenquadros.kovibes.api.playlist.models.PlaylistTracksResponse
import io.github.rubenquadros.kovibes.api.playlist.models.TrackItem
import io.github.rubenquadros.kovibes.api.response.PlaylistTracks
import io.github.rubenquadros.kovibes.api.response.Playlists

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