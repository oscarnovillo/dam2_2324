package com.example.composefullequip.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composefullequip.ui.screens.listado.PantallaLista

@Composable
fun Navigation()
{
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "listado",
    ) {
        composable(
            "listado"
        ) {
            PantallaLista(
            )
        }
        composable(
            "pantalla2"
        ) {
            Text("pantalla2")
        }
        composable(
            "pantalla3"
        ) {
            Text("pantalla3")
        }
    }



}
