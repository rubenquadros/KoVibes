package io.github.rubenquadros.kovibes.api.test.spotify

import io.github.rubenquadros.kovibes.api.KoVibesApi
import org.junit.Assert.assertThrows
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class KoVibesApiTest {

    private val spotifyApi = KoVibesApi

    @Test
    fun `cannot create the service with empty client id`()  {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            spotifyApi.createSpotifyService("", "SECRET")
        }

        assertEquals("Client Id cannot be empty.", exception.message)
    }

    @Test
    fun `cannot create the service with empty client secret`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            spotifyApi.createSpotifyService("ID", "")
        }

        assertEquals("Client secret cannot be empty.", exception.message)
    }

    @Test
    fun `providing valid client id and secret creates a spotify service`() {
        val spotifyService = spotifyApi.createSpotifyService("ID", "SECRET")

        assertNotNull(spotifyService)
    }
}