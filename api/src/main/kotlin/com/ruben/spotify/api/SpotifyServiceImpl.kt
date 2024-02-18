package com.ruben.spotify.api

import com.ruben.spotify.api.playlist.PlaylistApi
import com.ruben.spotify.api.playlist.models.FeaturedPlaylistsResponse
import com.ruben.spotify.api.playlist.models.PlaylistTracksResponse
import com.ruben.spotify.api.playlist.toFeaturedPlayLists
import com.ruben.spotify.api.playlist.toPlaylistTracks
import com.ruben.spotify.api.response.ErrorBody
import com.ruben.spotify.api.response.FeaturedPlaylists
import com.ruben.spotify.api.response.PlaylistTracks
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

    override suspend fun getPlaylistTracks(
        id: String,
        market: String?,
        fields: String?,
        limit: Int,
        offset: Int
    ): SpotifyApiResponse<PlaylistTracks, ErrorBody> {
        val response: ApiResponse<PlaylistTracksResponse, ErrorBody> =
            playlistApi.getPlaylistTracks(id, market, fields, limit, offset)

        return response.getParsedApiResponse { it.toPlaylistTracks() }
    }
}