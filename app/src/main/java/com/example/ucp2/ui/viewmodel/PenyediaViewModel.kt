package com.example.ucp2.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.AppToko
import com.example.ucp2.ui.viewmodel.barang.BarangViewModel
import com.example.ucp2.ui.viewmodel.barang.DetailBrngViewModel
import com.example.ucp2.ui.viewmodel.barang.HomeBrngViewModel
import com.example.ucp2.ui.viewmodel.barang.UpdateBrngViewModel
import com.example.ucp2.ui.viewmodel.suplayer.HomeSplyrViewModel
import com.example.ucp2.ui.viewmodel.suplayer.SuplayerViewModel

object PenyediaViewModel {

    val Factory = viewModelFactory {
        initializer {
            // ViewModel untuk halaman utama
            HomePageViewModel(
                tokoApp().containerApp.repositoryBrg,
                tokoApp().containerApp.repositorySpl
            )
        }
        initializer {
            // ViewModel untuk Home Barang
            HomeBrngViewModel(
                tokoApp().containerApp.repositoryBrg
            )
        }
        initializer {
            // ViewModel untuk Home Suplayer
            HomeSplyrViewModel(
                tokoApp().containerApp.repositorySpl
            )
        }
        initializer {
            // ViewModel untuk Barang
            BarangViewModel(
                tokoApp().containerApp.repositoryBrg
            )
        }
        initializer {
            // ViewModel untuk Detail Barang
            DetailBrngViewModel(
                createSavedStateHandle(),
                tokoApp().containerApp.repositoryBrg
            )
        }
        initializer {
            // ViewModel untuk Update Barang
            UpdateBrngViewModel(
                createSavedStateHandle(),
                tokoApp().containerApp.repositoryBrg
            )
        }
        initializer {
            // ViewModel untuk Suplayer
            SuplayerViewModel(
                tokoApp().containerApp.repositorySpl
            )
        }
    }
}

// Extension function untuk mengambil instance AppToko dari CreationExtras
fun CreationExtras.tokoApp(): AppToko =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AppToko)
