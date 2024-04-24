package io.github.rubenquadros.kovibes.api.playlist

import io.github.rubenquadros.kovibes.api.ApiResponse
import io.github.rubenquadros.kovibes.api.playlist.models.FeaturedPlaylistsResponse
import io.github.rubenquadros.kovibes.api.playlist.models.PlaylistTracksResponse
import io.github.rubenquadros.kovibes.api.response.ErrorBody

/**
 * Playlist API interface provides methods using which one can get get information about the playlists from the Spotify API.
 *
 * Each API returns [ApiResponse].
 */
internal interface PlaylistApi {

    /**
     * Get featured playlists API returns the current featured playlists on Spotify.
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
     * Get playlist tracks API returns all the tracks for the current playlist.
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