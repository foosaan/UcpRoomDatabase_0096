package com.example.ucp2.ui.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2.ui.view.Home
import com.example.ucp2.ui.view.barang.InsertBrgView
import com.example.ucp2.ui.view.barang.UpdateBrgView
import com.example.ucp2.view.barang.DetailBrngView
import com.example.ucp2.view.barang.HomeBrngView
import com.example.ucp_2pam.ui.View.Suplier.InsertSplView


@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route
    ) {
        composable(
            route = DestinasiHome.route
        ) {
            Home (
                onBarangClick = { navController.navigate(DestinasiHomeBrng.route) },
                onTambahBarangClick = { navController.navigate(DestinasiInsertBrng.route) },
                onSuplayerClick = { navController.navigate(DestinasiHomeSplyr.route) },
                onTambahSuplierClick = { navController.navigate(DestinasiInsertSplyr.route) },
            )
        }
        composable(
            route = DestinasiHomeBrng.route
        ){
            HomeBrngView(
                onDetailBrgClick = {id ->
                    navController.navigate("${DestinasiDetailBrng.route}/$id")
                    println("PengelolaHalaman: id= $id")
                },
                onAddBrgClick = {navController.navigate(DestinasiInsertBrng.route)},
                onBack = {navController.popBackStack()},
                modifier = Modifier
            )
        }
        composable(
            route = DestinasiHomeSplyr.route
        ){
            HomeBrngView(
                onBack = {navController.popBackStack()},
                modifier = Modifier
            )
        }
        composable(
            route = DestinasiInsertBrng.route
        ){
            InsertBrgView(
                onBack = {navController.popBackStack()},
                onNavigate = { },
                modifier = Modifier
            )
        }
        composable(
            DestinasiDetailBrng.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailBrng.id){
                    type = NavType.StringType
                }
            )
        ){
            val id = it.arguments?.getString(DestinasiDetailBrng.id)
            id?.let { id ->
                DetailBrngView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdate.route}/$it")
                    },
                    modifier = Modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )

            }
        }
        composable(
            DestinasiUpdate.routeWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdate.id){
                    type = NavType.StringType
                }
            )
        ){
            UpdateBrgView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = Modifier,
            )
        }
        composable(
            route = DestinasiInsertSplyr.route
        ){
            InsertSplView(
                onBack = {navController.popBackStack()},
                onNavigate = { },
                modifier = Modifier
            )
        }
    }
}