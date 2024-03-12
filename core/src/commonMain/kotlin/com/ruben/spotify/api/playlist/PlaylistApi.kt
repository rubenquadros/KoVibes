package com.ruben.spotify.api.playlist

import com.ruben.spotify.api.ApiResponse
import com.ruben.spotify.api.playlist.models.FeaturedPlaylistsResponse
import com.ruben.spotify.api.playlist.models.PlaylistTracksResponse
import com.ruben.spotify.api.response.ErrorBody

/**
 * Playlist API interface provides methods using which one can get get information about the playlists from the Spotify API.
 *
 * Each API returns [ApiResponse].
 */
internal interface PlaylistApi {

    /**
     * Get featured playlists returns the current featured playlists on Spotify.
     *
     * @param locale
     * @param limit
     * @param offset
     * @return [FeaturedPlaylistsResponse] when success and [ErrorBody] when error.
     */
    suspend fun getFeaturedPlaylists(
        locale: String,
        limit: Int,
        offset: Int
    ): ApiResponse<FeaturedPlaylistsResponse, ErrorBody>

    /**
     * Get playlist tracks returns all the tracks for the current playlist.
     *
     * @param id
     * @param market
     * @param fields
     * @param limit
     * @param offset
     * @return [PlaylistTracksResponse] when success and [ErrorBody] when error.
     */
    suspend fun getPlaylistTracks(
        id: String,
        market: String?,
        fields: String?,
        limit: Int,
        offset: Int
    ): ApiResponse<PlaylistTracksResponse, ErrorBody>

}