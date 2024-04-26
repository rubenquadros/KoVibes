package io.github.rubenquadros.kovibes.api.test.playlist

import io.github.rubenquadros.kovibes.api.playlist.models.FeaturedPlaylistsResponse
import io.github.rubenquadros.kovibes.api.playlist.toFeaturedPlayLists
import io.github.rubenquadros.kovibes.api.test.getExpectedResponse
import io.github.rubenquadros.kovibes.api.test.json
import kotlin.test.Test
import kotlin.test.assertTrue

class PlaylistApiMapperTest {

    @Test
    fun `featured playlist response is mapped to featured playlists`() {
        val featuredPlaylistResponseJson = json.decodeFromString<FeaturedPlaylistsResponse>(
            getExpectedResponse("playlist/featured_playlists.json")
        )

        val featuredPlaylists = featuredPlaylistResponseJson.toFeaturedPlayLists()

        assertTrue {
            featuredPlaylists.items.size == featuredPlaylistResponseJson.playlists.items.size
        }
    }

}