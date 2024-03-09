package com.ruben.spotify.example.android.featuredPlaylists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruben.spotify.api.response.Playlist
import com.ruben.spotify.example.android.repository.SpotifyRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class FeaturedPlaylistsViewModel(private val spotifyRepository: SpotifyRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<FeaturedPlaylistsState> =
        MutableStateFlow(FeaturedPlaylistsState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getFeaturedPlaylists()
    }

    private fun getFeaturedPlaylists() {
        viewModelScope.launch {
            val playlists = spotifyRepository.getFeaturedPlaylists()
            _uiState.value = FeaturedPlaylistsState.Result(playlists?.items?.toImmutableList())
        }
    }
}

sealed interface FeaturedPlaylistsState {
    data object Loading : FeaturedPlaylistsState

    data class Result(val playlists: ImmutableList<Playlist>?) : FeaturedPlaylistsState
}