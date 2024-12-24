package com.example.ucp2.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "suplayer")
data class Suplayer(
    @PrimaryKey
    val id: String,
    val namasuplayer: String,
    val kontak: String,
    val alamat: String,
)