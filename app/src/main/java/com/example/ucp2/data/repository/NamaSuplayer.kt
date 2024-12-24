package com.example.ucp2.data.repository

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.viewmodel.HomePageViewModel
import com.example.ucp2.ui.viewmodel.PenyediaViewModel

object NamaSuplayer {
    @Composable
    fun options(
        suplayerHomeViewModel: HomePageViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ): List<String> {
        val dataNama by suplayerHomeViewModel.homeSplUiState.collectAsState()
        val namasuplayer = dataNama.listSuplier.map { it.namasuplayer }
        return namasuplayer
    }
}