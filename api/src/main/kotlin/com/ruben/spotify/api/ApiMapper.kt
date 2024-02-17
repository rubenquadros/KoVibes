package com.ruben.spotify.api

import com.ruben.spotify.api.response.FeaturedPlaylists
import com.ruben.spotify.api.response.Image
import com.ruben.spotify.api.response.Playlist

internal fun FeaturedPlaylistsResponse.toFeaturedPlayLists(): FeaturedPlaylists {
    return FeaturedPlaylists(
        playlists = this.playlists.items.map { item: PlaylistItem ->
            Playlist(
                collaborative = item.collaborative,
                description = item.description,
                id = item.id,
                name = item.name,
                public = item.public,
                images = item.images.map { image: PlaylistImage ->
                    Image(
                        height = image.height,
                        width = image.width,
                        url = image.url
                    )
                }
            )
        }
    )
}