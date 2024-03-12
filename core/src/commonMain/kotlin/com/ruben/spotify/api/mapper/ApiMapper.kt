package com.ruben.spotify.api.mapper

import com.ruben.spotify.api.models.AlbumInfo
import com.ruben.spotify.api.models.ArtistInfo
import com.ruben.spotify.api.models.ImageInfo
import com.ruben.spotify.api.models.PlaylistInfo
import com.ruben.spotify.api.models.TrackInfo
import com.ruben.spotify.api.playlist.models.RestrictionInfo
import com.ruben.spotify.api.response.Album
import com.ruben.spotify.api.response.Artist
import com.ruben.spotify.api.response.Image
import com.ruben.spotify.api.response.Playlist
import com.ruben.spotify.api.response.Restrictions
import com.ruben.spotify.api.response.Track

/**
 * @suppress
 *
 * Map [ImageInfo] to [Image].
 */
internal fun ImageInfo.toImage(): Image {
    return Image(height = height, width = width, url = url.orEmpty())
}

/**
 * @suppress
 * Map [RestrictionInfo] to [Restrictions].
 */
internal fun RestrictionInfo.toRestrictions(): Restrictions {
    return Restrictions(reason)
}

/**
 * @suppress
 * Map [ArtistInfo] to [Artist].
 */
internal fun ArtistInfo.toArtist(): Artist {
    return Artist(
        followers = followers?.total,
        genres = genres,
        id = id,
        images = images?.map { it.toImage() },
        name = name,
        popularity = popularity ?: 0
    )
}

/**
 * @suppress
 * Map [AlbumInfo] to [Album].
 */
internal fun AlbumInfo.toAlbum(): Album {
    return Album(
        albumType = type,
        availableMarkets = availableMarkets,
        id = id,
        name = name,
        releaseDate = releaseDate,
        totalTracks = totalTracks,
        restrictions = restrictions?.let { restrictionInfo: RestrictionInfo ->
            restrictionInfo.toRestrictions()
        },
        images = images.map { imageInfo: ImageInfo ->
            imageInfo.toImage()
        },
        artists = artists.map { it.toArtist() }
    )
}

/**
 * @suppress
 * Map [TrackInfo] to [Track].
 */
internal fun TrackInfo.toTrack(addedAt: String? = null): Track {
    return Track(
        addedAt = addedAt,
        album = albumInfo.toAlbum(),
        artists = artistsInfo.map { it.toArtist() },
        availableMarkets = availableMarkets,
        restrictions = restrictions?.toRestrictions(),
        discNumber = discNumber,
        duration = discDuration,
        explicit = explicit,
        id = id,
        name = name,
        popularity = popularity,
        previewUrl = previewUrl
    )
}

/**
 * @suppress
 * Map [PlaylistInfo] to [Playlist].
 */
internal fun PlaylistInfo.toPlayList(): Playlist {
    return Playlist(
        collaborative = collaborative,
        description = description.orEmpty(),
        id = id,
        name = name,
        public = public ?: false,
        images = images.map { imageInfo: ImageInfo ->
            imageInfo.toImage()
        }
    )
}