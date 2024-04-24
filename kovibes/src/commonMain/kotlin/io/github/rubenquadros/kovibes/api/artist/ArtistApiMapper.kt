package io.github.rubenquadros.kovibes.api.artist

import io.github.rubenquadros.kovibes.api.artist.models.GetArtistTopTracksResponse
import io.github.rubenquadros.kovibes.api.mapper.toTrack
import io.github.rubenquadros.kovibes.api.response.ArtistTopTracks

/**
 * @suppress
 * Map [GetArtistTopTracksResponse] to [ArtistTopTracks].
 */
internal fun GetArtistTopTracksResponse.toArtistTopTracks(): ArtistTopTracks {
    return ArtistTopTracks(
        tracks = tracks.map { it.toTrack() }
    )
}