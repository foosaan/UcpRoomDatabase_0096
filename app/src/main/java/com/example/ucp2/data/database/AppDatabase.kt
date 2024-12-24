package com.example.ucp2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.data.dao.BarangDao
import com.example.ucp2.data.dao.SuplayerDao
import com.example.ucp2.entity.Barang
import com.example.ucp2.entity.Suplayer


@Database(entities = [Barang::class, Suplayer::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun barangDao(): BarangDao
    abstract fun suplayerDao(): SuplayerDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return (INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "AppDatabase"
                )
                    .build()
                    .also { INSTANCE = it }
            })
        }
    }
}