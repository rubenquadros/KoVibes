package io.github.rubenquadros.kovibes.example.jvm

import io.github.rubenquadros.kovibes.api.KoVibesApi
import io.github.rubenquadros.kovibes.api.request.GetRecommendationsRequest
import kotlinx.coroutines.runBlocking

fun main() {

    val spotifyService = KoVibesApi.createSpotifyService(
        clientId = System.getenv("CLIENT_ID"),
        clientSecret = System.getenv("CLIENT_SECRET")
    )

    runBlocking {
        val featuredPlaylistResponse = spotifyService.getFeaturedPlaylists()
        println("FeaturedPlaylistResponse :: $featuredPlaylistResponse")

        val playlistTracksResponse = spotifyService.getPlaylistTracks(id = "37i9dQZF1DXdGUQjVlqY2Q")
        println("PlaylistTracksResponse :: $playlistTracksResponse")

        val genresResponse = spotifyService.getGenres()
        println("GenresResponse :: $genresResponse")

        val categoriesResponse = spotifyService.getCategories()
        println("CategoriesResponse :: $categoriesResponse")

        val searchTrackResponse = spotifyService.searchTrack("rap")
        println("SearchTrackResponse :: $searchTrackResponse")

        val recommendationsResponse = spotifyService.getRecommendations(
            GetRecommendationsRequest(seedTracks = listOf("37i9dQZF1DXdGUQjVlqY2Q"))
        )
        println("RecommendationsResponse :: $recommendationsResponse")
    }
}