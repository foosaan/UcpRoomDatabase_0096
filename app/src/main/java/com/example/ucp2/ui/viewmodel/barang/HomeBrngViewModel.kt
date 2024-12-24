package com.example.ucp2.ui.viewmodel.barang

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.dao.repository.barang.RepositoryBrng
import com.example.ucp2.entity.Barang
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomeBrngViewModel(
    private val repositoryBrng: RepositoryBrng
) : ViewModel() {
    val DestinasiHomeBrng: StateFlow<HomeBrngUiState> = repositoryBrng.getAllBarang()
        .filterNotNull()
        .map {
            HomeBrngUiState(
                listBrng = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(HomeBrngUiState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeBrngUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeBrngUiState()
        )

}

data class HomeBrngUiState (
    val listBrng: List<Barang> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)