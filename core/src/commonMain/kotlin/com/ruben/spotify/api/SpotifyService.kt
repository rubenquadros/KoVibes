package com.ruben.spotify.api

import com.ruben.spotify.api.request.GetRecommendationsRequest
import com.ruben.spotify.api.response.Albums
import com.ruben.spotify.api.response.Artists
import com.ruben.spotify.api.response.Categories
import com.ruben.spotify.api.response.ErrorBody
import com.ruben.spotify.api.response.Genres
import com.ruben.spotify.api.response.PlaylistTracks
import com.ruben.spotify.api.response.Playlists
import com.ruben.spotify.api.response.Recommendations
import com.ruben.spotify.api.response.SpotifyApiResponse
import com.ruben.spotify.api.response.Tracks

/**
 * Spotify service interface provides all the methods to fetch data from the Spotify API.
 *
 * Each API returns [SpotifyApiResponse].
 */
interface SpotifyService {

    /**
     * Get featured playlists API returns the featured playlists.
     * This is a paginated API.
     *
     * `offset` is the page you are requesting for. Initially it will be 0.
     *
     * You will have to increment it for subsequent pages.
     *
     * You can know if there are more pages from [Playlists.isNext].
     *
     * See the [Spotify Doc](https://developer.spotify.com/documentation/web-api/reference/get-featured-playlists).
     *
     * @param locale
     * @param limit
     * @param offset
     * @return [Playlists] when success and [ErrorBody] when error.
     */
    suspend fun getFeaturedPlaylists(
        locale: String = "en_US",
        limit: Int = 20,
        offset: Int = 0
    ): SpotifyApiResponse<Playlists, ErrorBody>

    /**
     * Get playlist tracks API returns all the tracks for a particular playlist.
     * This is a paginated API.
     *
     * `offset` is the page you are requesting for. Initially it will be 0.
     *
     * You will have to increment it for subsequent pages.
     *
     * You can know if there are more pages from [PlaylistTracks.isNext].
     *
     * See the [Spotify Doc](https://developer.spotify.com/documentation/web-api/reference/get-playlists-tracks).
     *
     * @param id
     * @param market
     * @param fields
     * @param limit
     * @param offset
     * @return [PlaylistTracks] when success and [ErrorBody] when error.
     */
    suspend fun getPlaylistTracks(
        id: String,
        market: String? = null,
        fields: String? = null,
        limit: Int = 20,
        offset: Int = 0
    ): SpotifyApiResponse<PlaylistTracks, ErrorBody>

    /**
     * Get genres API returns all available genres.
     *
     * See the [Spotify Doc](https://developer.spotify.com/documentation/web-api/reference/get-recommendation-genres).
     *
     * @return [Genres] when success and [ErrorBody] when error.
     */
    suspend fun getGenres(): SpotifyApiResponse<Genres, ErrorBody>

    /**
     * Get categories API returns all available categories.
     * This is a paginated API.
     *
     * `offset` is the page you are requesting for. Initially it will be 0.
     *
     * You will have to increment it for subsequent pages.
     *
     * You can know if there are more pages from [Categories.isNext].
     *
     * See the [Spotify Doc](https://developer.spotify.com/documentation/web-api/reference/get-categories).
     *
     * @param locale
     * @param limit
     * @param offset
     * @return [Categories] when success and [ErrorBody] when error.
     */
    suspend fun getCategories(
        locale: String = "en_US",
        limit: Int = 20,
        offset: Int = 0
    ): SpotifyApiResponse<Categories, ErrorBody>

    /**
     * Search track API searches for a track from the given [query].
     * This is a paginated API.
     *
     * `offset` is the page you are requesting for. Initially it will be 0.
     *
     * You will have to increment it for subsequent pages.
     *
     * You can know if there are more pages from [Tracks.isNext].
     *
     * See the [Spotify Doc](https://developer.spotify.com/documentation/web-api/reference/search).
     *
     * @param query
     * @param market
     * @param limit
     * @param offset
     * @return [Tracks] when success and [ErrorBody] when error.
     */
    suspend fun searchTrack(
        query: String,
        market: String? = null,
        limit: Int = 20,
        offset: Int = 0
    ): SpotifyApiResponse<Tracks, ErrorBody>

    /**
     * Search album API searches for a album from the given [query].
     * This is a paginated API.
     *
     * `offset` is the page you are requesting for. Initially it will be 0.
     *
     * You will have to increment it for subsequent pages.
     *
     * You can know if there are more pages from [Albums.isNext].
     *
     * See the [Spotify Doc](https://developer.spotify.com/documentation/web-api/reference/search).
     *
     * @param query
     * @param market
     * @param limit
     * @param offset
     * @return [Albums] when success and [ErrorBody] when error.
     */
    suspend fun searchAlbum(
        query: String,
        market: String? = null,
        limit: Int = 20,
        offset: Int = 0
    ): SpotifyApiResponse<Albums, ErrorBody>

    /**
     * Search artist API searches for an artist from the given [query].
     * This is a paginated API.
     *
     * `offset` is the page you are requesting for. Initially it will be 0.
     *
     * You will have to increment it for subsequent pages.
     *
     * You can know if there are more pages from [Artists.isNext].
     *
     * See the [Spotify Doc](https://developer.spotify.com/documentation/web-api/reference/search).
     *
     * @param query
     * @param market
     * @param limit
     * @param offset
     * @return [Artists] when success and [ErrorBody] when error.
     */
    suspend fun searchArtist(
        query: String,
        market: String? = null,
        limit: Int = 20,
        offset: Int = 0
    ): SpotifyApiResponse<Artists, ErrorBody>

    /**
     * Search playlist API searches for a playlist from the given [query].
     * This is a paginated API.
     *
     * `offset` is the page you are requesting for. Initially it will be 0.
     *
     * You will have to increment it for subsequent pages.
     *
     * You can know if there are more pages from [Playlists.isNext].
     *
     * See the [Spotify Doc](https://developer.spotify.com/documentation/web-api/reference/search).
     *
     * @param query
     * @param market
     * @param limit
     * @param offset
     * @return [Playlists] when success and [ErrorBody] when error.
     */
    suspend fun searchPlaylist(
        query: String,
        market: String? = null,
        limit: Int = 20,
        offset: Int = 0
    ): SpotifyApiResponse<Playlists, ErrorBody>

    /**
     * Get recommendations API returns a list of recommended tracks.
     *
     * The recommendations are returned from the information provided via [GetRecommendationsRequest].
     *
     * See [Spotify Doc](https://developer.spotify.com/documentation/web-api/reference/get-recommendations).
     *
     * @param getRecommendationsRequest
     * @return [Recommendations] when success and [ErrorBody] when error.
     */
    suspend fun getRecommendations(
        getRecommendationsRequest: GetRecommendationsRequest
    ): SpotifyApiResponse<Recommendations, ErrorBody>
}