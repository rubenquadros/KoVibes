package com.ruben.spotify.api.search

import com.ruben.spotify.api.ApiResponse
import com.ruben.spotify.api.response.ErrorBody
import com.ruben.spotify.api.search.models.SearchAlbumResponse
import com.ruben.spotify.api.search.models.SearchArtistResponse
import com.ruben.spotify.api.search.models.SearchPlaylistResponse
import com.ruben.spotify.api.search.models.SearchTrackResponse

/**
 * Search API interface provides methods using which one can search for data from the Spotify API.
 *
 * Each API returns [ApiResponse].
 */
internal interface SearchApi {

    /**
     * Search track API searches for the tracks.
     *
     * @param query
     * @param market
     * @param limit
     * @param offset
     * @return [SearchTrackResponse] when success and [ErrorBody] when error.
     */
    suspend fun searchTrack(
        query: String,
        market: String?,
        limit: Int,
        offset: Int
    ): ApiResponse<SearchTrackResponse, ErrorBody>

    /**
     * Search album API searches for the albums.
     *
     * @param query
     * @param market
     * @param limit
     * @param offset
     * @return [SearchAlbumResponse] when success and [ErrorBody] when error.
     */
    suspend fun searchAlbum(
        query: String,
        market: String?,
        limit: Int,
        offset: Int
    ): ApiResponse<SearchAlbumResponse, ErrorBody>

    /**
     * Search artist API searches for the artists.
     *
     * @param query
     * @param market
     * @param limit
     * @param offset
     * @return [SearchArtistResponse] when success and [ErrorBody] when error.
     */
    suspend fun searchArtist(
        query: String,
        market: String?,
        limit: Int,
        offset: Int
    ): ApiResponse<SearchArtistResponse, ErrorBody>

    /**
     * Search playlist API searches for the playlists.
     *
     * @param query
     * @param market
     * @param limit
     * @param offset
     * @return [SearchPlaylistResponse] when success and [ErrorBody] when error.
     */
    suspend fun searchPlaylist(
        query: String,
        market: String?,
        limit: Int,
        offset: Int
    ): ApiResponse<SearchPlaylistResponse, ErrorBody>
}