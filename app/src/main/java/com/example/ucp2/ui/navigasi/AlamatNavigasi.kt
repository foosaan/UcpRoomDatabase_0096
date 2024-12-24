package com.example.ucp2.ui.navigasi

interface AlamatNavigasi{
    val route: String
}

object DestinasiHome : AlamatNavigasi {
    override val route = "Home"
}


object DestinasiHomeSplyr: AlamatNavigasi {
    override val route = "HomeSuplayer"
}

object DestinasiHomeBrng: AlamatNavigasi {
    override val route = "HomeBarang"
}

object DestinasiInsertSplyr: AlamatNavigasi {
    override val route = "Supalyer"
}

object DestinasiInsertBrng: AlamatNavigasi {
    override val route = "Barang"
}

object DestinasiDetailSplyr: AlamatNavigasi {
    override val route = "Detail"
    const val id = "id"
    val routeWithArg = "$route/{$id}"
}

object DestinasiDetailBrng: AlamatNavigasi {
    override val route = "detail"
    const val id = "id"
    val routeWithArg = "$route/{$id}"
}

object DestinasiUpdate: AlamatNavigasi {
    override val route = "update"
    const val id = "id"
    val routeWithArg = "$route/{$id}"
}