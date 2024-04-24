package io.github.rubenquadros.kovibes.api.artist

import io.github.rubenquadros.kovibes.api.ApiResponse
import io.github.rubenquadros.kovibes.api.artist.models.GetArtistTopTracksResponse
import io.github.rubenquadros.kovibes.api.models.AlbumResponse
import io.github.rubenquadros.kovibes.api.models.ArtistInfo
import io.github.rubenquadros.kovibes.api.response.ErrorBody

/**
 * Artist API interface provides methods using which one can get information about the artist.
 * You can get artists' top tracks, albums and also other related artists.
 *
 * Each API returns [ApiResponse].
 */
internal interface ArtistApi {

    /**
     * Get artist API returns the artist information from the given ID.
     *
     * @param id
     * @return [ArtistInfo] when success and [ErrorBody] when error.
     */
    suspend fun getArtist(id: String): ApiResponse<ArtistInfo, ErrorBody>

    /**
     * Get artist albums API returns the albums, single, features and compilations of the artist.
     *
     * @param id
     * @param includeGroups
     * @param market
     * @param limit
     * @param offset
     * @return [AlbumResponse] when success and [ErrorBody] when error.
     */
    suspend fun getArtistAlbums(
        id: String,
        includeGroups: List<String>,
        market: String?,
        limit: Int,
        offset: Int
    ): ApiResponse<AlbumResponse, ErrorBody>

    /**
     * Get artist top tracks API returns the top tracks of the artist.
     *
     * @param id
     * @param market
     * @return [GetArtistTopTracksResponse] when success and [ErrorBody] when error.
     */
    suspend fun getArtistTopTracks(
        id: String,
        market: String?
    ): ApiResponse<GetArtistTopTracksResponse, ErrorBody>
}