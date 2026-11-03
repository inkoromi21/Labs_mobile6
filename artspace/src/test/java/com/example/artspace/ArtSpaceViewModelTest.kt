package com.example.artspace

import org.junit.Assert.assertEquals
import org.junit.Test

class ArtSpaceViewModelTest {
  @Test
  fun navigationWrapsInBothDirections() {
    val viewModel = ArtSpaceViewModel()
    assertEquals(0, viewModel.uiState.value.currentImage)
    viewModel.previous()
    assertEquals(2, viewModel.uiState.value.currentImage)
    viewModel.next()
    assertEquals(0, viewModel.uiState.value.currentImage)
    repeat(3) { viewModel.next() }
    assertEquals(0, viewModel.uiState.value.currentImage)
  }
}
