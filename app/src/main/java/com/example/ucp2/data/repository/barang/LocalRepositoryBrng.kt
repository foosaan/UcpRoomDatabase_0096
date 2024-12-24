package com.example.ucp2.data.dao.repository.barang

import com.example.ucp2.data.dao.BarangDao
import com.example.ucp2.entity.Barang
import kotlinx.coroutines.flow.Flow


class LocalRepositoryBrng(
    private val barangDao: BarangDao
) : RepositoryBrng {
    override suspend fun insertBarang(barang: Barang) {
        barangDao.insertBarang(barang)
    }
    override suspend fun deleteBarang(barang: Barang) {
        barangDao.deleteBarang(barang)
    }
    override suspend fun updateBarang(barang: Barang) {
        barangDao.updateBarang(barang)
    }
    override fun getAllBarang(): Flow<List<Barang>> =
        barangDao.getAllBarang()

    override fun getBarang(id: String): Flow<Barang> =
        barangDao.getBarang(id)
}