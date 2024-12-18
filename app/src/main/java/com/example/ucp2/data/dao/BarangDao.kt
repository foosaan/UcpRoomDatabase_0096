package com.example.ucp2.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2.data.entity.Barang
import kotlinx.coroutines.flow.Flow

@Dao
interface BarangDao {

    @Insert
    suspend fun insertBarang(barang: Barang)

    //getAllBarang
    @Query("SELECT * FROM Barang ORDER BY nama ASC")
    fun getAllBarang(): Flow<List<Barang>>

    //getBarang
    @Query("SELECT * FROM barang WHERE id = :id")
    fun getDetailBarang(id: Int): Barang

    //deleteBarang
    @Delete
    suspend fun deleteBarang(barang: Barang)

    @Update
    suspend fun updateBarang(barang: Barang)
}