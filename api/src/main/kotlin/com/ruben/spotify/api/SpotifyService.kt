package com.ruben.spotify.api

import com.ruben.spotify.api.response.ErrorBody
import com.ruben.spotify.api.response.FeaturedPlaylists
import com.ruben.spotify.api.response.PlaylistTracks
import com.ruben.spotify.api.response.SpotifyApiResponse

interface SpotifyService {
    suspend fun getFeaturedPlaylists(
        locale: String = "en_US",
        limit: Int = 20,
        offset: Int = 0
    ): SpotifyApiResponse<FeaturedPlaylists, ErrorBody>

    suspend fun getPlaylistTracks(
        id: String,
        market: String? = null,
        fields: String? = null,
        limit: Int = 20,
        offset: Int = 0
    ): SpotifyApiResponse<PlaylistTracks, ErrorBody>
}