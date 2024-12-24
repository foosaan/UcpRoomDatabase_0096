package com.example.ucp2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucp2.entity.Suplayer
import kotlinx.coroutines.flow.Flow


@Dao
interface SuplayerDao {
    @Insert
    suspend fun insert(suplayer: Suplayer)

    @Query("SELECT * FROM suplayer WHERE id = :id")
    fun getSuplayer(id: Int): Flow<Suplayer>

    @Query("SELECT * FROM suplayer ORDER BY namasuplayer ASC")
    fun getAllSuplayer(): Flow<List<Suplayer>>
}