package com.example.ucp2.data.dependenciesinjection


import android.content.Context
import com.example.ucp2.data.dao.repository.barang.LocalRepositoryBrng
import com.example.ucp2.data.dao.repository.barang.RepositoryBrng
import com.example.ucp2.data.database.AppDatabase
import com.example.ucp2.data.repository.suplayer.LocalRepositorySplyr
import com.example.ucp2.data.repository.suplayer.RepositorySplyr

interface InterfaceContainerApp{
    val repositoryBrg: RepositoryBrng
    val repositorySpl: RepositorySplyr
}

class ContainerApp(private val context: Context) : InterfaceContainerApp{
    override val repositoryBrg: RepositoryBrng by lazy {
        LocalRepositoryBrng(AppDatabase.getDatabase(context).barangDao())
    }
    override val repositorySpl: RepositorySplyr by lazy {
        LocalRepositorySplyr(AppDatabase.getDatabase(context).suplayerDao())
    }
}