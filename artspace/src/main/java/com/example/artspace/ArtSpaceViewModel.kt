package com.example.artspace

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ArtSpaceUiState(val currentImage: Int = 0)

class ArtSpaceViewModel : ViewModel() {
  private val _uiState = MutableStateFlow(ArtSpaceUiState())
  val uiState = _uiState.asStateFlow()

  fun previous() {
    _uiState.update { it.copy(currentImage = (it.currentImage + 2) % 3) }
  }

  fun next() {
    _uiState.update { it.copy(currentImage = (it.currentImage + 1) % 3) }
  }
}
