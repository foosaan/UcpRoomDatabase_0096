package com.example.ucp2.data.repository.suplayer

import com.example.ucp2.data.dao.SuplayerDao
import com.example.ucp2.entity.Suplayer
import kotlinx.coroutines.flow.Flow


class LocalRepositorySplyr(
    private val suplayerDao: SuplayerDao
) : RepositorySplyr {
    override suspend fun insertSuplayer(suplayer: Suplayer) {
        suplayerDao.insert(suplayer)
    }
    override fun getAllSuplayer(): Flow<List<Suplayer>> =
        suplayerDao.getAllSuplayer()

    override fun getSuplayer(id: Int): Flow<Suplayer> =
        suplayerDao.getSuplayer(id)
}