package com.example.ucp2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucp2.data.entity.Suplayer

@Dao
interface SuplayerDao {
    @Insert
    suspend fun insert(suplier: Suplayer)

    @Query("SELECT * FROM suplayer")
    suspend fun getAll(): List<Suplayer>
}