package com.example.ucp2.ui.viewmodel.suplayer


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.repository.suplayer.RepositorySplyr
import com.example.ucp2.entity.Suplayer
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomeSplyrViewModel(
    private val repositorySpl: RepositorySplyr
) : ViewModel() {

    val HomeSplUiState: StateFlow<HomeSplUiState> = repositorySpl.getAllSuplayer()
        .filterNotNull()
        .map {
            HomeSplUiState(
                listSpl = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(HomeSplUiState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeSplUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeSplUiState(
                isLoading = true
            )
        )
}

data class HomeSplUiState(
    val listSpl: List<Suplayer> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)