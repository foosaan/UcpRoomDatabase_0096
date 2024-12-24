package com.example.ucp2.ui.viewmodel.barang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.dao.repository.barang.RepositoryBrng
import com.example.ucp2.entity.Barang
import com.example.ucp2.ui.navigasi.DestinasiUpdate

import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateBrngViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryBrng: RepositoryBrng,
) : ViewModel() {

    var UpdateBrngUiState by mutableStateOf(BrngUiState())
        private set

    private val barangId: String = checkNotNull(savedStateHandle[DestinasiUpdate.id])

    init {
        viewModelScope.launch {
            UpdateBrngUiState = repositoryBrng.getBarang(barangId)
                .filterNotNull()
                .first()
                .toUIStateBrng()
        }
    }

    fun updateState(barangEvent: BarangEvent) {
        UpdateBrngUiState = UpdateBrngUiState.copy(barangEvent = barangEvent)
    }

    // Validate input fields
    fun validateFields(): Boolean {
        val event = UpdateBrngUiState.barangEvent
        val errorState = FormBrngErrorState(
            id = if (event.id.isEmpty()) "Id barang tidak boleh kosong" else null,
            nama = if (event.nama.isEmpty()) "Nama barang tidak boleh kosong" else null,
            deskripsi = if (event.deskripsi.isEmpty()) "Deskripsi barang tidak boleh kosong" else null,
            harga = if (event.harga <= 0) "Harga barang tidak boleh kosong atau negatif" else null,
            stok = if (event.stok <= 0) "Stok barang tidak boleh kosong atau negatif" else null,
            namasuplayer = if (event.namasuplayer.isEmpty()) "Nama suplier tidak boleh kosong" else null
        )

        UpdateBrngUiState = UpdateBrngUiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    // Update the barang data
    fun updateData() {
        val currentEvent = UpdateBrngUiState.barangEvent

        // Validate the fields before saving
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    // Update barang data in the repository
                    repositoryBrng.updateBarang(currentEvent.toBarangEntity())

                    // Success: Update the state with a success message
                    UpdateBrngUiState = UpdateBrngUiState.copy(
                        snackbarMessage = "Data Berhasil Diupdate",
                        barangEvent = BarangEvent(), // Clear form after successful update
                        isEntryValid = FormBrngErrorState()
                    )
                } catch (e: Exception) {
                    // Failure: Update the state with an error message
                    UpdateBrngUiState = UpdateBrngUiState.copy(
                        snackbarMessage = "Data Gagal Diupdate"
                    )
                }
            }
        } else {
            // Validation failed: Show validation message
            UpdateBrngUiState = UpdateBrngUiState.copy(
                snackbarMessage = "Periksa kembali data Anda"
            )
        }
    }

    // Reset snackbar message
    fun resetSnackbarMessage() {
        UpdateBrngUiState = UpdateBrngUiState.copy(
            snackbarMessage = null
        )
    }
}

// Extension function to convert a Barang entity to UI state
fun Barang.toUIStateBrng(): BrngUiState = BrngUiState(
    barangEvent = this.toDetailUiEvent()
)
