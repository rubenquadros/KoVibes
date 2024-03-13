package io.github.rubenquadros.kovibes.api.test.playlist

import io.github.rubenquadros.kovibes.api.playlist.models.FeaturedPlaylistsResponse
import io.github.rubenquadros.kovibes.api.playlist.toFeaturedPlayLists
import io.github.rubenquadros.kovibes.api.test.getExpectedResponse
import kotlinx.serialization.json.Json
import org.junit.Test
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