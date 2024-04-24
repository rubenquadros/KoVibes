package io.github.rubenquadros.kovibes.api.test

import io.github.rubenquadros.kovibes.api.models.AlbumInfo
import io.github.rubenquadros.kovibes.api.models.ArtistInfo
import io.github.rubenquadros.kovibes.api.models.ExternalUrls
import io.github.rubenquadros.kovibes.api.models.ImageInfo
import io.github.rubenquadros.kovibes.api.playlist.models.RestrictionInfo
import kotlinx.serialization.json.Json
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

const val errorResponsePath = "error.json"

const val clientId = "client_id"

const val clientSecret = "client_secret"

const val authToken = "auth_token"

@OptIn(ExperimentalEncodingApi::class)
internal val encodedCred = Base64.encode(
    source = "$clientId:$clientSecret".toByteArray()
)

internal val imageInfo = ImageInfo(
    height = 200,
    width = 200,
    url = "https://vibesync.image.png"
)

internal val restrictionInfo = RestrictionInfo(
    reason = "Reason for restriction"
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
    artists = listOf(),
    restrictions = restrictionInfo
)

internal val artistInfo = Json.decodeFromString<ArtistInfo>(
    getExpectedResponse("artist/artist.json")
)