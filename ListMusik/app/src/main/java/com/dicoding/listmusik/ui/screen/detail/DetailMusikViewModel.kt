package com.dicoding.listmusik.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.listmusik.MusikRepository
import com.dicoding.listmusik.model.Musik
import com.dicoding.listmusik.model.MusikList
import com.dicoding.listmusik.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class DetailMusikViewModel(
    private val repository: MusikRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<MusikList>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<MusikList>>
        get() = _uiState

    fun getMusikById(musikId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getMusikById(musikId))
        }
    }


fun addToFavorite(reward: Musik, count: Int) {
    viewModelScope.launch {
        repository.updateMusik(reward.id, count)
    }
}
}