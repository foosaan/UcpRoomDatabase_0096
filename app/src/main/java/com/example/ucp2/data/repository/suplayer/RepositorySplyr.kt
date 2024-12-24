package com.example.ucp2.data.repository.suplayer

import com.example.ucp2.entity.Suplayer
import kotlinx.coroutines.flow.Flow


interface RepositorySplyr {
    suspend fun insertSuplayer(suplayer: Suplayer)
    fun getAllSuplayer(): Flow<List<Suplayer>>
    fun getSuplayer(id: Int): Flow<Suplayer>
}