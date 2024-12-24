package com.example.ucp2.ui.viewmodel.suplayer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.repository.suplayer.RepositorySplyr
import com.example.ucp2.entity.Suplayer
import kotlinx.coroutines.launch

class SuplayerViewModel(private val repositorySup: RepositorySplyr) : ViewModel() {

    var suplayerUiState by mutableStateOf(SuplayerUiState())
        private set

    fun updateUiState(suplayerEvent: SuplayerEvent) {
        suplayerUiState = suplayerUiState.copy(
            suplayerEvent = suplayerEvent
        )
    }

    private fun validateFields(): Boolean {
        val event = suplayerUiState.suplayerEvent
        val errorState = FormSuplayerErrorState(
            idsuplayer = if (event.idsuplayer.isEmpty()) "Id suplayer tidak boleh kosong" else null,
            namasuplayer = if (event.namasuplayer.isEmpty()) "Nama suplayer tidak boleh kosong" else null,
            kontak = if (event.kontak.isEmpty()) "Kontak suplayer tidak boleh kosong" else null,
            alamat = if (event.alamat.isEmpty()) "Alamat suplayer tidak boleh kosong" else null
        )
        suplayerUiState = suplayerUiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData() {
        val currentEvent = suplayerUiState.suplayerEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositorySup.insertSuplayer(currentEvent.toSuplayerEntity())
                    suplayerUiState = suplayerUiState.copy(
                        snackbarMessage = "Data Berhasil Disimpan",
                        suplayerEvent = SuplayerEvent(),
                        isEntryValid = FormSuplayerErrorState()
                    )
                } catch (e: Exception) {
                    suplayerUiState = suplayerUiState.copy(
                        snackbarMessage = "Data Gagal Disimpan: ${e.message}"
                    )
                }
            }
        } else {
            suplayerUiState = suplayerUiState.copy(
                snackbarMessage = "Input tidak valid. Periksa kembali data Anda"
            )
        }
    }

    fun resetSnackbarMessage() {
        suplayerUiState = suplayerUiState.copy(
            snackbarMessage = null
        )
    }
}

data class SuplayerUiState(
    val suplayerEvent: SuplayerEvent = SuplayerEvent(),
    val isEntryValid: FormSuplayerErrorState = FormSuplayerErrorState(),
    val snackbarMessage: String? = null
)

data class FormSuplayerErrorState(
    val idsuplayer: String? = null,
    val namasuplayer: String? = null,
    val kontak: String? = null,
    val alamat: String? = null
) {
    fun isValid(): Boolean {
        return idsuplayer == null && namasuplayer == null && kontak == null && alamat == null
    }
}

data class SuplayerEvent(
    val idsuplayer: String = "",
    val namasuplayer: String = "",
    val kontak: String = "",
    val alamat: String = ""
)

fun SuplayerEvent.toSuplayerEntity(): Suplayer = Suplayer(
    id = idsuplayer,
    namasuplayer = namasuplayer,
    kontak = kontak,
    alamat = alamat
)
