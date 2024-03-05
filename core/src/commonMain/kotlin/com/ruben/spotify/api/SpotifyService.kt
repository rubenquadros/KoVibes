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

interface SpotifyService {

    suspend fun getFeaturedPlaylists(
        locale: String = "en_US",
        limit: Int = 20,
        offset: Int = 0
    ): SpotifyApiResponse<Playlists, ErrorBody>

    suspend fun getPlaylistTracks(
        id: String,
        market: String? = null,
        fields: String? = null,
        limit: Int = 20,
        offset: Int = 0
    ): SpotifyApiResponse<PlaylistTracks, ErrorBody>

    suspend fun getGenres(): SpotifyApiResponse<Genres, ErrorBody>

    suspend fun getCategories(
        locale: String = "en_US",
        limit: Int = 20,
        offset: Int = 0
    ): SpotifyApiResponse<Categories, ErrorBody>

    suspend fun searchTrack(
        query: String,
        market: String? = null,
        limit: Int = 20,
        offset: Int = 0
    ): SpotifyApiResponse<Tracks, ErrorBody>

    suspend fun searchAlbum(
        query: String,
        market: String? = null,
        limit: Int = 20,
        offset: Int = 0
    ): SpotifyApiResponse<Albums, ErrorBody>

    suspend fun searchArtist(
        query: String,
        market: String? = null,
        limit: Int = 20,
        offset: Int = 0
    ): SpotifyApiResponse<Artists, ErrorBody>

    suspend fun searchPlaylist(
        query: String,
        market: String? = null,
        limit: Int = 20,
        offset: Int = 0
    ): SpotifyApiResponse<Playlists, ErrorBody>

    suspend fun getRecommendations(
        getRecommendationsRequest: GetRecommendationsRequest
    ): SpotifyApiResponse<Recommendations, ErrorBody>
}