package com.ruben.spotify.api.test.playlist

import com.ruben.spotify.api.playlist.models.FeaturedPlaylistsResponse
import com.ruben.spotify.api.playlist.toFeaturedPlayLists
import com.ruben.spotify.api.test.getExpectedResponse
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class PlaylistApiMapperTest {

    @Test
    fun `featured playlist response is mapped to featured playlists`() {
        val featuredPlaylistResponseJson = Json.decodeFromString<FeaturedPlaylistsResponse>(
            getExpectedResponse("playlist/featured_playlists.json")
        )

        val featuredPlaylists = featuredPlaylistResponseJson.toFeaturedPlayLists()

        assertTrue {
            featuredPlaylists.items.size == featuredPlaylistResponseJson.playlists.items.size
        }
    }

}