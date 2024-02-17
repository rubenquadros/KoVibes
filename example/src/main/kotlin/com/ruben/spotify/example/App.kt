package com.ruben.spotify.example

import com.ruben.spotify.api.SpotifyApi
import kotlinx.coroutines.runBlocking

fun main() {

    val spotifyService = SpotifyApi.createSpotifyApi(
        clientId = System.getenv("CLIENT_ID"),
        clientSecret = System.getenv("CLIENT_SECRET")
    )

    runBlocking {
        val response = spotifyService.getFeaturedPlaylists()
        println("Response :: $response")
    }
}