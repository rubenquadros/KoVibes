package com.ruben.spotify.api

import com.ruben.spotify.api.response.ErrorBody
import io.ktor.client.request.get
import io.ktor.http.parameters
import io.ktor.http.path
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

internal interface PlaylistApi {

    suspend fun getFeaturedPlaylists(
        locale: String,
        limit: Int,
        offset: Int
    ): ApiResponse<FeaturedPlaylistsResponse, ErrorBody>

}

internal class PlaylistApiImpl(
    private val ktorService: KtorService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : PlaylistApi {
    override suspend fun getFeaturedPlaylists(
        locale: String,
        limit: Int,
        offset: Int
    ): ApiResponse<FeaturedPlaylistsResponse, ErrorBody> {
        val response = withContext(dispatcher) {
            ktorService.client.get {
                url {
                    path("/v1/browse/featured-playlists")
                }
                parameters {
                    this["locale"] = locale
                    this["limit"] = limit.toString()
                    this["offset"] = offset.toString()
                }
            }
        }

        return response.getParsedHttpResponse<FeaturedPlaylistsResponse, ErrorBody>()
    }
}

@Serializable
internal data class FeaturedPlaylistsResponse(
    @SerialName("message")
    val message: String,
    @SerialName("playlists")
    val playlists: Playlists
)

@Serializable
internal data class Playlists(
    @SerialName("href")
    val href: String,
    @SerialName("limit")
    val limit: Int,
    @SerialName("next")
    val next: String?,
    @SerialName("offset")
    val offset: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("previous")
    val previous: String?,
    @SerialName("items")
    val items: List<PlaylistItem>
)

@Serializable
internal data class PlaylistItem(
    @SerialName("collaborative")
    val collaborative: Boolean,
    @SerialName("description")
    val description: String?,
    @SerialName("external_urls")
    val externalUrls: ExternalUrls,
    @SerialName("href")
    val href: String,
    @SerialName("id")
    val id: String,
    @SerialName("images")
    val images: List<PlaylistImage>,
    @SerialName("name")
    val name: String,
    @SerialName("owner")
    val owner: PlaylistOwner,
    @SerialName("primary_color")
    val primaryColor: String,
    @SerialName("public")
    val public: Boolean,
    @SerialName("snapshot_id")
    val snapshotId: String,
    @SerialName("tracks")
    val tracks: PlaylistTracks,
    @SerialName("type")
    val type: String,
    @SerialName("uri")
    val uri: String
)

@Serializable
internal data class ExternalUrls(
    @SerialName("spotify")
    val spotify: String
)

@Serializable
internal data class PlaylistImage(
    @SerialName("height")
    val height: Int?,
    @SerialName("width")
    val width: Int?,
    @SerialName("url")
    val url: String?
)

@Serializable
internal data class PlaylistOwner(
    @SerialName("display_name")
    val displayName: String,
    @SerialName("external_urls")
    val externalUrls: ExternalUrls,
    @SerialName("href")
    val href: String,
    @SerialName("id")
    val id: String,
    @SerialName("type")
    val type: String,
    @SerialName("uri")
    val uri: String
)

@Serializable
internal data class PlaylistTracks(
    @SerialName("href")
    val href: String,
    @SerialName("total")
    val total: Int
)