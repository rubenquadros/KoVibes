package com.ruben.spotify.api

import com.ruben.spotify.api.response.ErrorBody
import com.ruben.spotify.api.response.FeaturedPlaylists
import com.ruben.spotify.api.response.SpotifyApiResponse

internal class SpotifyServiceImpl(
    private val playlistApi: PlaylistApi
) : SpotifyService {
    override suspend fun getFeaturedPlaylists(
        locale: String,
        limit: Int,
        offset: Int
    ): SpotifyApiResponse<FeaturedPlaylists, ErrorBody> {
        val response: ApiResponse<FeaturedPlaylistsResponse, ErrorBody> =
            playlistApi.getFeaturedPlaylists(locale, limit, offset)
        return response.getParsedApiResponse { it.toFeaturedPlayLists() }
    }
}