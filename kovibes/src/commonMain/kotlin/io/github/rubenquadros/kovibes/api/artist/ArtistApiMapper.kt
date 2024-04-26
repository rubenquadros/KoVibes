package io.github.rubenquadros.kovibes.api.artist

import io.github.rubenquadros.kovibes.api.artist.models.GetArtistTopTracksResponse
import io.github.rubenquadros.kovibes.api.artist.models.GetRelatedArtistsResponse
import io.github.rubenquadros.kovibes.api.mapper.toArtist
import io.github.rubenquadros.kovibes.api.mapper.toTrack
import io.github.rubenquadros.kovibes.api.response.ArtistTopTracks
import io.github.rubenquadros.kovibes.api.response.RelatedArtists

/**
 * @suppress
 * Map [GetArtistTopTracksResponse] to [ArtistTopTracks].
 */
internal fun GetArtistTopTracksResponse.toArtistTopTracks(): ArtistTopTracks {
    return ArtistTopTracks(
        tracks = tracks.map { it.toTrack() }
    )
}

/**
 * @suppress
 * Map [GetRelatedArtistsResponse] to [RelatedArtists]
 */
internal fun GetRelatedArtistsResponse.toRelatedArtists(): RelatedArtists {
    return RelatedArtists(
        artists = artists.map { it.toArtist() }
    )
}