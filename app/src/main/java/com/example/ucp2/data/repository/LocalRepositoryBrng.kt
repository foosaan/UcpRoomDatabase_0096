package com.example.ucp2.data.repository

import com.example.ucp2.data.dao.BarangDao
import com.example.ucp2.data.entity.Barang

class LocalRepositoryBrng (
    private val barangDao: BarangDao
) : RepositoryBrng{
    }
