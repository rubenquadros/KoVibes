package io.github.rubenquadros.kovibes.api.test

import io.github.rubenquadros.kovibes.api.models.AlbumInfo
import io.github.rubenquadros.kovibes.api.models.ArtistInfo
import io.github.rubenquadros.kovibes.api.models.ExternalUrls
import io.github.rubenquadros.kovibes.api.models.ImageInfo
import kotlinx.serialization.json.Json

const val errorResponsePath = "error.json"

internal val imageInfo = ImageInfo(
    height = 200,
    width = 200,
    url = "https://vibesync.image.png"
)

internal val albumInfo = AlbumInfo(
    albumType = "album",
    albumGroup = "album",
    name = "Dreamville",
    id = "1234",
    href = "https://vibesync.album.href",
    type = "album",
    uri = "spotify:album:6fyR4wBPwLHKcRtxgd4sGh",
    totalTracks = 20,
    releaseDate = "2024-01-01",
    releaseDatePrecision = "day",
    externalUrls = ExternalUrls(
        spotify = "spotify"
    ),
    images = listOf(imageInfo),
    artists = listOf()
)

internal val artistInfo = Json.decodeFromString<ArtistInfo>(
    getExpectedResponse("artist/artist.json")
)