package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class DessertUiState(
  val revenue: Int = 0,
  val dessertsSold: Int = 0,
  val dessertImageId: Int = Datasource.dessertList.first().imageId,
  val dessertPrice: Int = Datasource.dessertList.first().price
)

class DessertViewModel : ViewModel() {
  private val _uiState = MutableStateFlow(DessertUiState())
  val uiState = _uiState.asStateFlow()

  fun sellDessert() {
    _uiState.update { state ->
      val sold = state.dessertsSold + 1
      val dessert = Datasource.dessertList.last { sold >= it.startProductionAmount }
      state.copy(
        revenue = state.revenue + state.dessertPrice,
        dessertsSold = sold,
        dessertImageId = dessert.imageId,
        dessertPrice = dessert.price
      )
    }
  }
}
