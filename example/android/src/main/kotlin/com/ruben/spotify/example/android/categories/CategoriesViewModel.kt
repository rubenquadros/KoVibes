package com.ruben.spotify.example.android.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruben.spotify.api.response.Category
import com.ruben.spotify.example.android.repository.SpotifyRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CategoriesViewModel(private val spotifyRepository: SpotifyRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<CategoriesState> =
        MutableStateFlow(CategoriesState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            val categories = spotifyRepository.getCategories()

            _uiState.update {
                CategoriesState.Result(categories?.items?.toImmutableList())
            }
        }
    }
}

sealed interface CategoriesState {
    data object Loading: CategoriesState

    data class Result(val categories: ImmutableList<Category>?): CategoriesState
}