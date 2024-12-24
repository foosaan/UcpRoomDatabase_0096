package com.example.ucp2.data.dao.repository.barang

import com.example.ucp2.entity.Barang
import kotlinx.coroutines.flow.Flow


interface RepositoryBrng {
    suspend fun insertBarang(barang: Barang)
    fun getAllBarang(): Flow<List<Barang>>
    fun getBarang(id: String): Flow<Barang>
    suspend fun deleteBarang(barang: Barang)
    suspend fun updateBarang(barang: Barang)
}