package io.github.rubenquadros.kovibes.api.album

import io.github.rubenquadros.kovibes.api.ApiResponse
import io.github.rubenquadros.kovibes.api.album.models.GetAlbumTracksResponse
import io.github.rubenquadros.kovibes.api.album.models.GetNewAlbumReleasesResponse
import io.github.rubenquadros.kovibes.api.models.AlbumInfo
import io.github.rubenquadros.kovibes.api.response.ErrorBody

/**
 * Album API interface provides methods using which one can get all the information about an album.
 *
 * Each API returns [ApiResponse]
 */
internal interface AlbumApi {

    /**
     * Get album API returns the information about an album.
     *
     * @param id
     * @param market
     * @return [AlbumInfo] when success and [ErrorBody] when error.
     */
    suspend fun getAlbum(id: String, market: String?): ApiResponse<AlbumInfo, ErrorBody>

    /**
     * Get album tracks API returns all the tracks in an album.
     *
     * @param id
     * @param market
     * @param limit
     * @param offset
     * @return [GetAlbumTracksResponse] when success and [ErrorBody] when error.
     */
    suspend fun getAlbumTracks(
        id: String,
        market: String?,
        limit: Int,
        offset: Int
    ): ApiResponse<GetAlbumTracksResponse, ErrorBody>

    /**
     * Get new album releases API returns all the albums that have newly released.
     *
     * @param limit
     * @param offset
     * @return [GetNewAlbumReleasesResponse] when success and [ErrorBody] when error.
     */
    suspend fun getNewAlbumReleases(
        limit: Int,
        offset: Int
    ): ApiResponse<GetNewAlbumReleasesResponse, ErrorBody>
}