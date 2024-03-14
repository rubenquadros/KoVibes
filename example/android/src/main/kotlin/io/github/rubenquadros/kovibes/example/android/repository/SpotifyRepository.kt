package io.github.rubenquadros.kovibes.example.android.repository

import io.github.rubenquadros.kovibes.api.response.Categories
import io.github.rubenquadros.kovibes.api.response.Genres
import io.github.rubenquadros.kovibes.api.response.Playlists

interface SpotifyRepository {

    suspend fun getFeaturedPlaylists(): Playlists?

    suspend fun getCategories(): Categories?

    suspend fun getGenres(): Genres?

}