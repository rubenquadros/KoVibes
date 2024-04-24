package io.github.rubenquadros.kovibes.api

import io.github.rubenquadros.kovibes.api.artist.ArtistApi
import io.github.rubenquadros.kovibes.api.artist.toArtistTopTracks
import io.github.rubenquadros.kovibes.api.browse.BrowseApi
import io.github.rubenquadros.kovibes.api.browse.toCategories
import io.github.rubenquadros.kovibes.api.mapper.toAlbums
import io.github.rubenquadros.kovibes.api.mapper.toArtist
import io.github.rubenquadros.kovibes.api.playlist.PlaylistApi
import io.github.rubenquadros.kovibes.api.playlist.models.FeaturedPlaylistsResponse
import io.github.rubenquadros.kovibes.api.playlist.models.PlaylistTracksResponse
import io.github.rubenquadros.kovibes.api.playlist.toFeaturedPlayLists
import io.github.rubenquadros.kovibes.api.playlist.toPlaylistTracks
import io.github.rubenquadros.kovibes.api.recommendations.RecommendationsApi
import io.github.rubenquadros.kovibes.api.recommendations.toRecommendations
import io.github.rubenquadros.kovibes.api.request.GetRecommendationsRequest
import io.github.rubenquadros.kovibes.api.response.Albums
import io.github.rubenquadros.kovibes.api.response.Artist
import io.github.rubenquadros.kovibes.api.response.ArtistTopTracks
import io.github.rubenquadros.kovibes.api.response.Artists
import io.github.rubenquadros.kovibes.api.response.Categories
import io.github.rubenquadros.kovibes.api.response.ErrorBody
import io.github.rubenquadros.kovibes.api.response.Genres
import io.github.rubenquadros.kovibes.api.response.PlaylistTracks
import io.github.rubenquadros.kovibes.api.response.Playlists
import io.github.rubenquadros.kovibes.api.response.Recommendations
import io.github.rubenquadros.kovibes.api.response.SpotifyApiResponse
import io.github.rubenquadros.kovibes.api.response.Tracks
import io.github.rubenquadros.kovibes.api.search.SearchApi
import io.github.rubenquadros.kovibes.api.search.toSearchArtist
import io.github.rubenquadros.kovibes.api.search.toSearchPlaylist
import io.github.rubenquadros.kovibes.api.search.toSearchTrack

/**
 * @suppress
 * SpotifyServiceImpl is the implementation of [SpotifyService].
 *
 * @property playlistApi
 * @property browseApi
 * @property searchApi
 * @property recommendationsApi
 * @property artistApi
 */
internal class SpotifyServiceImpl(
    private val playlistApi: PlaylistApi,
    private val browseApi: BrowseApi,
    private val searchApi: SearchApi,
    private val recommendationsApi: RecommendationsApi,
    private val artistApi: ArtistApi
) : SpotifyService {
    override suspend fun getFeaturedPlaylists(
        locale: String,
        limit: Int,
        offset: Int
    ): SpotifyApiResponse<Playlists, ErrorBody> {
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

    override suspend fun getGenres(): SpotifyApiResponse<Genres, ErrorBody> {
        val response = browseApi.getGenres()

        return response.getParsedApiResponse { it }
    }

    override suspend fun getCategories(
        locale: String,
        limit: Int,
        offset: Int
    ): SpotifyApiResponse<Categories, ErrorBody> {
        val response = browseApi.getCategories(locale, limit, offset)

        return response.getParsedApiResponse { it.toCategories() }
    }

    override suspend fun searchTrack(
        query: String,
        market: String?,
        limit: Int,
        offset: Int
    ): SpotifyApiResponse<Tracks, ErrorBody> {
        val response = searchApi.searchTrack(query, market, limit, offset)

        return response.getParsedApiResponse { it.toSearchTrack() }
    }

    override suspend fun searchAlbum(
        query: String,
        market: String?,
        limit: Int,
        offset: Int
    ): SpotifyApiResponse<Albums, ErrorBody> {
        val response = searchApi.searchAlbum(query, market, limit, offset)

        return response.getParsedApiResponse { it.albumResponse.toAlbums() }
    }

    override suspend fun searchArtist(
        query: String,
        market: String?,
        limit: Int,
        offset: Int
    ): SpotifyApiResponse<Artists, ErrorBody> {
        val response = searchApi.searchArtist(query, market, limit, offset)

        return response.getParsedApiResponse { it.toSearchArtist() }
    }

    override suspend fun searchPlaylist(
        query: String,
        market: String?,
        limit: Int,
        offset: Int
    ): SpotifyApiResponse<Playlists, ErrorBody> {
        val response = searchApi.searchPlaylist(query, market, limit, offset)

        return response.getParsedApiResponse { it.toSearchPlaylist() }
    }

    override suspend fun getRecommendations(getRecommendationsRequest: GetRecommendationsRequest): SpotifyApiResponse<Recommendations, ErrorBody> {
        val response = recommendationsApi.getRecommendations(getRecommendationsRequest)

        return response.getParsedApiResponse { it.toRecommendations() }
    }

    override suspend fun getArtist(id: String): SpotifyApiResponse<Artist, ErrorBody> {
        val response = artistApi.getArtist(id)

        return response.getParsedApiResponse { it.toArtist() }
    }

    override suspend fun getArtistAlbums(
        id: String,
        includeGroups: List<String>,
        market: String?,
        limit: Int,
        offset: Int
    ): SpotifyApiResponse<Albums, ErrorBody> {
        val response = artistApi.getArtistAlbums(id, includeGroups, market, limit, offset)

        return response.getParsedApiResponse { it.toAlbums() }
    }

    override suspend fun getArtistTopTracks(
        id: String,
        market: String?
    ): SpotifyApiResponse<ArtistTopTracks, ErrorBody> {
        val response = artistApi.getArtistTopTracks(id, market)

        return response.getParsedApiResponse { it.toArtistTopTracks() }
    }
}