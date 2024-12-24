package com.example.ucp2.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2.R
import com.example.ucp2.ui.costumeWidget.HomeBar

@Composable
fun Home(
    onBarangClick: () -> Unit,
    onTambahBarangClick: () -> Unit,
    onSuplayerClick: () -> Unit,
    onTambahSuplierClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            HomeBar(
                showBackButton = false,
                onBack = { },
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color(0xFFE8F5E9)) // Warna latar belakang hijau pastel
            ) {
                // Header teks di atas kartu
                HeaderText()

                // Kolom dengan tampilan kartu menumpuk ke bawah
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp) // Spasi antar kartu lebih besar
                ) {
                    // Tambah Barang
                    CardItem(
                        title = "Tambah Barang",
                        gradientColors = listOf(Color(0xFF81C784), Color(0xFF388E3C)), // Hijau lembut ke gelap
                        iconRes = R.drawable.tambahbarang,
                        onClick = onTambahBarangClick
                    )
                    // Barang List
                    CardItem(
                        title = "List Barang",
                        gradientColors = listOf(Color(0xFF64B5F6), Color(0xFF1976D2)), // Biru lembut ke gelap
                        iconRes = R.drawable.listbarang,
                        onClick = onBarangClick
                    )
                    // Tambah Suplier
                    CardItem(
                        title = "Tambah Suplier",
                        gradientColors = listOf(Color(0xFFFFD54F), Color(0xFFF57F17)), // Kuning lembut ke jingga
                        iconRes = R.drawable.tambahsuplayer,
                        onClick = onTambahSuplierClick
                    )
                    // Suplier List
                    CardItem(
                        title = "List Suplier",
                        gradientColors = listOf(Color(0xFFE57373), Color(0xFFD32F2F)), // Merah lembut ke gelap
                        iconRes = R.drawable.listsuplayer,
                        onClick = onSuplayerClick
                    )
                }
            }
        }
    )
}

@Composable
fun CardItem(
    title: String,
    gradientColors: List<Color>,
    iconRes: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp) // Tinggi kartu lebih besar
            .background(
                brush = Brush.linearGradient(colors = gradientColors),
                shape = RoundedCornerShape(20.dp) // Sudut lebih melengkung
            )
            .clickable { onClick() }
            .padding(16.dp), // Padding di dalam kartu
        contentAlignment = Alignment.Center
    ) {
        // Gambar ikon kecil di atas teks
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp) // Ukuran ikon lebih besar
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(12.dp)) // Spasi antara ikon dan teks
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun HeaderText() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Aplikasi Pengolahan Barang",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B5E20) // Hijau tua
        )
        Text(
            text = "Kelola data barang dan suplier dengan mudah.",
            fontSize = 16.sp,
            color = Color(0xFF4E342E) // Cokelat lembut
        )
    }
}
