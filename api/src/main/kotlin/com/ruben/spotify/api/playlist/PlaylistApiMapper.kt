package com.ruben.spotify.api.playlist

import com.ruben.spotify.api.mapper.toImage
import com.ruben.spotify.api.mapper.toTrack
import com.ruben.spotify.api.models.ImageInfo
import com.ruben.spotify.api.playlist.models.FeaturedPlaylistsResponse
import com.ruben.spotify.api.playlist.models.PlaylistItem
import com.ruben.spotify.api.playlist.models.PlaylistTracksResponse
import com.ruben.spotify.api.playlist.models.TrackItem
import com.ruben.spotify.api.response.FeaturedPlaylists
import com.ruben.spotify.api.response.Playlist
import com.ruben.spotify.api.response.PlaylistTracks

internal fun FeaturedPlaylistsResponse.toFeaturedPlayLists(): FeaturedPlaylists {
    return FeaturedPlaylists(
        items = this.playlists.items.map { item: PlaylistItem ->
            Playlist(
                collaborative = item.collaborative,
                description = item.description.orEmpty(),
                id = item.id,
                name = item.name,
                public = item.public,
                images = item.images.map { imageInfo: ImageInfo ->
                    imageInfo.toImage()
                }
            )
        },
        isNext = this.playlists.next != null
    )
}

internal fun PlaylistTracksResponse.toPlaylistTracks(): PlaylistTracks {
    return PlaylistTracks(
        tracks = this.items.map { item: TrackItem ->
            item.trackInfo.toTrack(item.addedAt.orEmpty())
        },
        isNext = this.next != null
    )
}