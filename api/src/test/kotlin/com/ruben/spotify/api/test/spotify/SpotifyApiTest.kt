package com.ruben.spotify.api.test.spotify

import com.ruben.spotify.api.SpotifyApi
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class SpotifyApiTest {

    private val spotifyApi = SpotifyApi

    @Test
    fun `cannot create the service with empty client id`()  {
        val exception = assertThrows<IllegalArgumentException> {
            spotifyApi.createSpotifyApi("", "SECRET")
        }

        assertEquals("Client Id cannot be empty.", exception.message)
    }

    @Test
    fun `cannot create the service with empty client secret`() {
        val exception = assertThrows<IllegalArgumentException> {
            spotifyApi.createSpotifyApi("ID", "")
        }

        assertEquals("Client secret cannot be empty.", exception.message)
    }

    @Test
    fun `providing valid client id and secret creates a spotify service`() {
        val spotifyService = spotifyApi.createSpotifyApi("ID", "SECRET")

        assertNotNull(spotifyService)
    }
}