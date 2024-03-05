package com.ruben.spotify.api

import com.ruben.spotify.api.browse.BrowseApiImpl
import com.ruben.spotify.api.playlist.PlaylistApiImpl
import com.ruben.spotify.api.recommendations.RecommendationsApiImpl
import com.ruben.spotify.api.search.SearchApiImpl

object SpotifyApi {

    private val authStorage: AuthStorage by lazy { AuthStorage() }

    private val ktorService: KtorService by lazy {
        KtorService(authStorage = authStorage, ktorEngine = { getKtorEngine() })
    }

    private val spotifyService: SpotifyService by lazy {
        SpotifyServiceImpl(
            playlistApi = PlaylistApiImpl(ktorService),
            browseApi = BrowseApiImpl(ktorService),
            searchApi = SearchApiImpl(ktorService),
            recommendationsApi = RecommendationsApiImpl(ktorService)
        )
    }

    fun createSpotifyApi(clientId: String, clientSecret: String): SpotifyService {
        validateClientId(clientId)
        validateClientSecret(clientSecret)

        authStorage.init(clientId, clientSecret)
        return spotifyService
    }

    private fun validateClientId(clientId: String) {
        require(clientId.isNotEmpty()) {
            "Client Id cannot be empty."
        }
    }

    private fun validateClientSecret(clientSecret: String) {
        require(clientSecret.isNotEmpty()) {
            "Client secret cannot be empty."
        }
    }
}