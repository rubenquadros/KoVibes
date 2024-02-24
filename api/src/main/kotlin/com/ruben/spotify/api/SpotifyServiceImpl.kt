package com.ruben.spotify.api

import com.ruben.spotify.api.browse.BrowseApi
import com.ruben.spotify.api.browse.toCategories
import com.ruben.spotify.api.playlist.PlaylistApi
import com.ruben.spotify.api.playlist.models.FeaturedPlaylistsResponse
import com.ruben.spotify.api.playlist.models.PlaylistTracksResponse
import com.ruben.spotify.api.playlist.toFeaturedPlayLists
import com.ruben.spotify.api.playlist.toPlaylistTracks
import com.ruben.spotify.api.response.Albums
import com.ruben.spotify.api.response.Artists
import com.ruben.spotify.api.response.Categories
import com.ruben.spotify.api.response.ErrorBody
import com.ruben.spotify.api.response.Genres
import com.ruben.spotify.api.response.PlaylistTracks
import com.ruben.spotify.api.response.Playlists
import com.ruben.spotify.api.response.SpotifyApiResponse
import com.ruben.spotify.api.response.Tracks
import com.ruben.spotify.api.search.SearchApi
import com.ruben.spotify.api.search.toSearchAlbum
import com.ruben.spotify.api.search.toSearchArtist
import com.ruben.spotify.api.search.toSearchPlaylist
import com.ruben.spotify.api.search.toSearchTrack

internal class SpotifyServiceImpl(
    private val playlistApi: PlaylistApi,
    private val browseApi: BrowseApi,
    private val searchApi: SearchApi
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

        return response.getParsedApiResponse { it.toSearchAlbum() }
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
}