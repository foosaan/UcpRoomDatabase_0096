package com.example.ucp2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ucp2.data.dao.BarangDao
import com.example.ucp2.data.dao.SuplayerDao
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.data.entity.Suplayer

@Database(entities = [Barang::class, Suplayer::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun barangDao(): BarangDao
    abstract fun suplierDao(): SuplayerDao
}
