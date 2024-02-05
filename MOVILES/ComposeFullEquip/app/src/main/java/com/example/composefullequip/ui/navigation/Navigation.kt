package com.example.composefullequip.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composefullequip.ui.common.BottomBar
import com.example.composefullequip.ui.screens.detalle.PantallaDetalle
import com.example.composefullequip.ui.screens.lista.PantallaLista
import com.example.composefullequip.ui.screens.lista.PersonaItem


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
                onViewDetalle = {uuid ->
                    navController.navigate("detalle/${uuid}")
                },
                bottomNavigationBar =  {
                    BottomBar(
                        navController = navController,
                        screens = screensBottomBar)
                }
            )
        }
        composable(
            route =  "detalle/{personaId}",
            arguments = listOf(
                navArgument(name = "personaId") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            PantallaDetalle(
                personaId = it.arguments?.getString("personaId") ?: "" ,
                )
        }
        composable(
            "pantalla"
        ) {

            val snackbarHostState = remember { SnackbarHostState() }
            Scaffold (
                snackbarHost = { SnackbarHost(snackbarHostState) },
                bottomBar =  { BottomBar(
                    navController = navController,
                    screens = screensBottomBar) },

            ) { innerPadding ->
                Text(
                    modifier = Modifier.padding(
                        innerPadding),
                    text = "pantalla")


            }

        }
    }



}
