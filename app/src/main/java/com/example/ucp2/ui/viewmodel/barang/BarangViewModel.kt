package com.example.ucp2.ui.viewmodel.barang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.dao.repository.barang.RepositoryBrng
import com.example.ucp2.entity.Barang
import kotlinx.coroutines.launch

class BarangViewModel(private val repositoryBrng: RepositoryBrng) : ViewModel() {
    var BrngUiState by mutableStateOf(BrngUiState())

    fun updateUiState(barangEvent: BarangEvent) {
        BrngUiState = BrngUiState.copy(
            barangEvent = barangEvent
        )
    }

    private fun validateFields(): Boolean {
        val event = BrngUiState.barangEvent
        val errorState = FormBrngErrorState(
            id = if (event.id.isEmpty()) "Id tidak boleh kosong" else null,
            nama = if (event.nama.isEmpty()) "Nama tidak boleh kosong" else null,
            deskripsi = if (event.deskripsi.isEmpty()) "Deskripsi barang tidak boleh kosong" else null,
            harga = if (event.harga <= 0) "Harga barang tidak boleh kosong atau negatif" else null,
            stok = if (event.stok <= 0) "Stok barang tidak boleh kosong atau negatif" else null,
            namasuplayer = if (event.namasuplayer.isEmpty()) "Nama suplayer tidak boleh kosong" else null
        )
        BrngUiState = BrngUiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData() {
        val currentEvent = BrngUiState.barangEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryBrng.insertBarang(currentEvent.toBarangEntity())
                    BrngUiState = BrngUiState.copy(
                        snackbarMessage = "Data Berhasil Disimpan",
                        barangEvent = BarangEvent(),
                        isEntryValid = FormBrngErrorState()
                    )
                } catch (e: Exception) {
                    BrngUiState = BrngUiState.copy(
                        snackbarMessage = "Data Gagal Disimpan"
                    )
                }
            }
        } else {
            BrngUiState = BrngUiState.copy(
                snackbarMessage = "Input tidak valid. Periksa kembali data Anda"
            )
        }
    }

    fun resetSnackbarMessage() {
        BrngUiState = BrngUiState.copy(
            snackbarMessage = null
        )
    }
}

data class BrngUiState(
    val barangEvent: BarangEvent = BarangEvent(),
    val isEntryValid: FormBrngErrorState = FormBrngErrorState(),
    val snackbarMessage: String? = null
)

data class FormBrngErrorState(
    val id: String? = null,
    val nama: String? = null,
    val deskripsi: String? = null,
    val harga: String? = null,
    val stok: String? = null,
    val namasuplayer: String? = null // Diubah menjadi nullable
) {
    fun isValid(): Boolean {
        return id == null && nama == null && deskripsi == null &&
                harga == null && stok == null && namasuplayer == null
    }
}

data class BarangEvent(
    val id: String = "",
    val nama: String = "",
    val deskripsi: String = "",
    val harga: Int = 0,
    val stok: Int = 0,
    val namasuplayer: String = ""
)

fun BarangEvent.toBarangEntity(): Barang = Barang(
    id = id,
    nama = nama,
    deskripsi = deskripsi,
    harga = harga,
    stok = stok,
    namasuplayer = namasuplayer
)
