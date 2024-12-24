package com.example.ucp2.ui.CustomWidget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2.R

@Composable
fun TopAppBar(
    onBack: () -> Unit,
    showBackButton: Boolean = true,
    judul: String,
) {
    // Define custom colors for background, text, and icons
    val backgroundColor = Color(0xFF4CAF50) // Green color
    val textColor = Color.White
    val iconColor = Color.White

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(backgroundColor)
            .padding(horizontal = 24.dp, vertical = 16.dp) // Modified padding values
    ) {
        if (showBackButton) {
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(top = 8.dp) // Modified padding for IconButton
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Kembali",
                    tint = iconColor
                )
            }
        }

        Text(
            text = judul,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 8.dp) // Modified padding for Text
        )

        Icon(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            tint = iconColor,
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.CenterEnd)
                .padding(top = 16.dp, end = 10.dp) // Modified padding for Icon
        )
    }
}