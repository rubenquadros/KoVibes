package io.github.rubenquadros.kovibes.api

import io.github.rubenquadros.kovibes.api.request.GetRecommendationsRequest
import io.github.rubenquadros.kovibes.api.response.Album
import io.github.rubenquadros.kovibes.api.response.AlbumTracks
import io.github.rubenquadros.kovibes.api.response.Albums
import io.github.rubenquadros.kovibes.api.response.Artist
import io.github.rubenquadros.kovibes.api.response.ArtistTopTracks
import io.github.rubenquadros.kovibes.api.response.Artists
import io.github.rubenquadros.kovibes.api.response.Categories
import io.github.rubenquadros.kovibes.api.response.ErrorBody
import io.github.rubenquadros.kovibes.api.response.Genres
import io.github.rubenquadros.kovibes.api.response.NewAlbumReleases
import io.github.rubenquadros.kovibes.api.response.Playlist
import io.github.rubenquadros.kovibes.api.response.PlaylistTracks
import io.github.rubenquadros.kovibes.api.response.Playlists
import io.github.rubenquadros.kovibes.api.response.Recommendations
import io.github.rubenquadros.kovibes.api.response.RelatedArtists
import io.github.rubenquadros.kovibes.api.response.SpotifyApiResponse
import io.github.rubenquadros.kovibes.api.response.Tracks

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
     * Get playlist API returns information about the current playlist.
     *
     * See the [Spotify doc](https://developer.spotify.com/documentation/web-api/reference/get-playlist).
     *
     * @param id
     * @param market
     * @param fields
     * @param additionalTypes
     * @return [Playlist] when success and [ErrorBody] when error.
     */
    suspend fun getPlaylist(
        id: String,
        market: String? = null,
        fields: List<String>? = null,
        additionalTypes: List<String> = listOf("track")
    ): SpotifyApiResponse<Playlist, ErrorBody>

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
        fields: List<String>? = null,
        additionalTypes: List<String> = listOf("track"),
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
     * See the [Spotify Doc](https://developer.spotify.com/documentation/web-api/reference/get-recommendations).
     *
     * @param getRecommendationsRequest
     * @return [Recommendations] when success and [ErrorBody] when error.
     */
    suspend fun getRecommendations(
        getRecommendationsRequest: GetRecommendationsRequest
    ): SpotifyApiResponse<Recommendations, ErrorBody>

    /**
     * Get artist API returns the information about the artist.
     *
     * See the [Spotify Doc](https://developer.spotify.com/documentation/web-api/reference/get-an-artist).
     *
     * @param id
     * @return [Artist] when success and [ErrorBody] when error.
     */
    suspend fun getArtist(id: String): SpotifyApiResponse<Artist, ErrorBody>

    /**
     * Get artist albums API returns all the albums of the artist.
     * This is a paginated API.
     *
     * `offset` is the page you are requesting for. Initially it will be 0.
     *
     * You will have to increment it for subsequent pages.
     *
     * You can know if there are more pages from [Albums.isNext].
     *
     * See the [Spotify doc](https://developer.spotify.com/documentation/web-api/reference/get-an-artists-albums).
     *
     * @param id
     * @param includeGroups
     * @param market
     * @param limit
     * @param offset
     * @return [Albums] when success and [ErrorBody] when error.
     */
    suspend fun getArtistAlbums(
        id: String,
        includeGroups: List<String> = listOf("album"),
        market: String? = null,
        limit: Int = 20,
        offset: Int = 0
    ): SpotifyApiResponse<Albums, ErrorBody>

    /**
     * Get artist top tracks API returns the top tracks of the artist.
     *
     * See the [Spotify doc](https://developer.spotify.com/documentation/web-api/reference/get-an-artists-top-tracks).
     *
     * @param id
     * @param market
     * @return [ArtistTopTracks] when success and [ErrorBody] when error.
     */
    suspend fun getArtistTopTracks(
        id: String,
        market: String? = null
    ): SpotifyApiResponse<ArtistTopTracks, ErrorBody>

    /**
     * Get related artists API returns the artists similar to the given artist.
     *
     * See the [Spotify doc](https://developer.spotify.com/documentation/web-api/reference/get-an-artists-related-artists).
     *
     * @param id
     * @return [RelatedArtists] when success and [ErrorBody] when error.
     */
    suspend fun getRelatedArtists(id: String): SpotifyApiResponse<RelatedArtists, ErrorBody>

    /**
     * Get album API returns the details about an album.
     *
     * See the [Spotify doc](https://developer.spotify.com/documentation/web-api/reference/get-an-album).
     *
     * @param id
     * @param market
     * @return [Album] when success and [ErrorBody] when error.
     */
    suspend fun getAlbum(id: String, market: String? = null): SpotifyApiResponse<Album, ErrorBody>

    /**
     * Get album tracks API returns all the tracks in an album.
     * This is a paginated API.
     *
     * `offset` is the page you are requesting for. Initially it will be 0.
     *
     * You will have to increment it for subsequent pages.
     *
     * You can know if there are more pages from [Tracks.isNext].
     *
     * See the [Spotify doc](https://developer.spotify.com/documentation/web-api/reference/get-an-albums-tracks).
     *
     * @param id
     * @param market
     * @param limit
     * @param offset
     * @return [AlbumTracks] when success and [ErrorBody] when error.
     */
    suspend fun getAlbumTracks(
        id: String,
        market: String? = null,
        limit: Int = 20,
        offset: Int = 0
    ): SpotifyApiResponse<AlbumTracks, ErrorBody>

    /**
     * Get new album releases API returns all the new album releases.
     * This is a paginated API.
     *
     * `offset` is the page you are requesting for. Initially it will be 0.
     *
     * You will have to increment it for subsequent pages.
     *
     * You can know if there are more pages from [NewAlbumReleases.isNext].
     *
     * See the [Spotify doc](https://developer.spotify.com/documentation/web-api/reference/get-new-releases).
     *
     * @param limit
     * @param offset
     * @return [NewAlbumReleases] when success and [ErrorBody] when error.
     */
    suspend fun getNewAlbumReleases(
        limit: Int = 20,
        offset: Int = 0
    ): SpotifyApiResponse<NewAlbumReleases, ErrorBody>
}