package com.example.composefullequip.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composefullequip.ui.screens.detalle.PantallaDetalle
import com.example.composefullequip.ui.screens.lista.PantallaLista


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
                bottomNavigationBar = {
                   NavigationBar(){
                       val state =  navController.currentBackStackEntryAsState()
                       val currentDestination = state.value?.destination
                       screensBottomBar.forEach {screen ->
                           NavigationBarItem(
                               icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                               label = { Text(screen.route) },
                               selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                               onClick = {
                                   navController.navigate(screen.route) {
                                       // Pop up to the start destination of the graph to
                                       // avoid building up a large stack of destinations
                                       // on the back stack as users select items
                                       popUpTo(navController.graph.findStartDestination().id) {
                                           saveState = true
                                       }
                                       // Avoid multiple copies of the same destination when
                                       // reselecting the same item
                                       launchSingleTop = true
                                       // Restore state when reselecting a previously selected item
                                       restoreState = true
                                   }
                               }
                           )

                       }

                   }
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
            Text("pantalla")
        }
    }



}
