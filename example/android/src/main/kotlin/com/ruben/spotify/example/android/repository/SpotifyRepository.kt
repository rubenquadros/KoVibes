package com.ruben.spotify.example.android.repository

import com.ruben.spotify.api.response.Categories
import com.ruben.spotify.api.response.Genres
import com.ruben.spotify.api.response.Playlists

interface SpotifyRepository {

    suspend fun getFeaturedPlaylists(): Playlists?

    suspend fun getCategories(): Categories?

    suspend fun getGenres(): Genres?

}