package com.example.dessertclicker

import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.ui.DessertViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class DessertViewModelTest {
  @Test
  fun dessertChangesAtProductionThreshold() {
    val viewModel = DessertViewModel()
    val first = Datasource.dessertList.first()
    val second = Datasource.dessertList[1]
    repeat(second.startProductionAmount) { viewModel.sellDessert() }
    val state = viewModel.uiState.value
    assertEquals(second.startProductionAmount, state.dessertsSold)
    assertEquals(first.price * second.startProductionAmount, state.revenue)
    assertEquals(second.imageId, state.dessertImageId)
    viewModel.sellDessert()
    assertEquals(state.revenue + second.price, viewModel.uiState.value.revenue)
  }
}
