package com.example.ucp_2pam.ui.View.Suplier

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.CustomWidget.TopAppBar
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.suplayer.FormSuplayerErrorState
import com.example.ucp2.ui.viewmodel.suplayer.SuplayerEvent
import com.example.ucp2.ui.viewmodel.suplayer.SuplayerUiState
import com.example.ucp2.ui.viewmodel.suplayer.SuplayerViewModel
import kotlinx.coroutines.launch

@Composable
fun InsertSplView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SuplayerViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.suplayerUiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackbarMessage) {
        uiState.snackbarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackbarMessage()
            }
        }
    }

    Scaffold(
        Modifier, snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Suplier"
            )
            InsertBodySpl(
                uiState = uiState,
                onValueChange = { updateEvent ->
                    viewModel.updateUiState(updateEvent)
                },
                onClick = {
                    viewModel.saveData()
                    onNavigate()
                }
            )
        }
    }
}

@Composable
fun InsertBodySpl(
    modifier: Modifier = Modifier,
    onValueChange: (SuplayerEvent) -> Unit,
    uiState: SuplayerUiState,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormSuplier(
            suplierEvent = uiState.suplayerEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(text = "Simpan", color = Color.White)
        }
    }
}

@Composable
fun FormSuplier(
    suplierEvent: SuplayerEvent,
    onValueChange: (SuplayerEvent) -> Unit = {},
    errorState: FormSuplayerErrorState = FormSuplayerErrorState(),
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.idsuplayer,
            onValueChange = { onValueChange(suplierEvent.copy(idsuplayer = it)) },
            label = { Text(text = "Id Suplier") },
            isError = errorState.idsuplayer != null,
            placeholder = { Text(text = "Masukkan Id Suplayer") },
        )
        Text(
            text = errorState.idsuplayer ?: "",
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.namasuplayer,
            onValueChange = { onValueChange(suplierEvent.copy(namasuplayer = it)) },
            label = { Text(text = "Nama Suplier") },
            isError = errorState.namasuplayer != null,
            placeholder = { Text(text = "Masukkan Nama Suplier") },
        )
        Text(
            text = errorState.namasuplayer ?: "",
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.kontak,
            onValueChange = { onValueChange(suplierEvent.copy(kontak = it)) },
            label = { Text(text = "Kontak") },
            isError = errorState.kontak != null,

        )
        Text(
            text = errorState.kontak ?: "",
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.alamat,
            onValueChange = { onValueChange(suplierEvent.copy(alamat = it)) },
            label = { Text(text = "Alamat") },
            isError = errorState.alamat != null,
        )
        Text(
            text = errorState.alamat ?: "",
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.CustomWidget.TopAppBar
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.suplayer.FormSuplayerErrorState
import com.example.ucp2.ui.viewmodel.suplayer.SuplayerEvent
import com.example.ucp2.ui.viewmodel.suplayer.SuplayerUiState
import com.example.ucp2.ui.viewmodel.suplayer.SuplayerViewModel
import kotlinx.coroutines.launch

@Composable
fun InsertSplView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SuplayerViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.suplayerUiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackbarMessage) {
        uiState.snackbarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackbarMessage()
            }
        }
    }

    Scaffold(
        Modifier, snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Suplier"
            )
            InsertBodySpl(
                uiState = uiState,
                onValueChange = { updateEvent ->
                    viewModel.updateUiState(updateEvent)
                },
                onClick = {
                    viewModel.saveData()
                    onNavigate()
                }
            )
        }
    }
}

@Composable
fun InsertBodySpl(
    modifier: Modifier = Modifier,
    onValueChange: (SuplayerEvent) -> Unit,
    uiState: SuplayerUiState,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormSuplier(
            suplierEvent = uiState.suplayerEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(text = "Simpan", color = Color.White)
        }
    }
}

@Composable
fun FormSuplier(
    suplierEvent: SuplayerEvent,
    onValueChange: (SuplayerEvent) -> Unit = {},
    errorState: FormSuplayerErrorState = FormSuplayerErrorState(),
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.idsuplayer,
            onValueChange = { onValueChange(suplierEvent.copy(idsuplayer = it)) },
            label = { Text(text = "Id Suplier") },
            isError = errorState.idsuplayer != null,
            placeholder = { Text(text = "Masukkan Id Suplier") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.Gray
            )
        )
        Text(
            text = errorState.idsuplayer ?: "",
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.namasuplayer,
            onValueChange = { onValueChange(suplierEvent.copy(namasuplayer = it)) },
            label = { Text(text = "Nama Suplier") },
            isError = errorState.namasuplayer != null,
            placeholder = { Text(text = "Masukkan Nama Suplier") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.Gray
            )
        )
        Text(
            text = errorState.namasuplayer ?: "",
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.kontak,
            onValueChange = { onValueChange(suplierEvent.copy(kontak = it)) },
            label = { Text(text = "Kontak") },
            isError = errorState.kontak != null,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.Gray
            )
        )
        Text(
            text = errorState.kontak ?: "",
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.alamat,
            onValueChange = { onValueChange(suplierEvent.copy(alamat = it)) },
            label = { Text(text = "Alamat") },
            isError = errorState.alamat != null,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.Gray
            )
        )
        Text(
            text = errorState.alamat ?: "",
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
