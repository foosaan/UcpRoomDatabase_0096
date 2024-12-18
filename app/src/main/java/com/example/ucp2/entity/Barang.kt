package com.example.ucp2.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Barang")
data class Barang(
    @PrimaryKey
    val id: String,
    val nama: String,
    val deskripsi: String,
    val harga: Int,
    val stok: Int,
    val namasuplayer: String
)