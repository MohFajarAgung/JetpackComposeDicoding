package com.dicoding.listmusik.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.listmusik.MusikRepository
import com.dicoding.listmusik.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: MusikRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<FavoriteState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<FavoriteState>>
        get() = _uiState

    fun getAddedFavorite() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getFavoriteMusik()
                .collect { musik ->
                    _uiState.value = UiState.Success(FavoriteState(musik))
                }
        }
    }

}