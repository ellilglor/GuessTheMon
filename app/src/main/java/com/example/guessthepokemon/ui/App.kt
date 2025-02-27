package com.example.guessthepokemon.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.guessthepokemon.ui.layout.BottomNavBar
import com.example.guessthepokemon.ui.layout.NavDrawer
import com.example.guessthepokemon.ui.layout.NavRail
import com.example.guessthepokemon.ui.navigation.NavigationHost
import com.example.guessthepokemon.ui.navigation.NavigationRoutes
import com.example.guessthepokemon.ui.navigation.NavigationTypes

@Composable
fun App(
    navigationType: NavigationTypes,
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    when (navigationType) {
        NavigationTypes.BOTTOM_NAVIGATION -> {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    BottomNavBar(
                        goHome = {
                            navController.popBackStack(NavigationRoutes.Home.name, inclusive = false)
                        },
                        navigate = {
                            route -> navController.navigate(route) { launchSingleTop = true }
                        },
                        backStackEntry = backStackEntry
                    )
                }
            ) { innerPadding ->
                NavigationHost(navController, innerPadding, navigationType)
            }
        }
        NavigationTypes.NAVIGATION_RAIL -> {
            Row {
                NavRail(
                    goHome = {
                        navController.popBackStack(NavigationRoutes.Home.name, inclusive = false)
                    },
                    navigate = {
                        route -> navController.navigate(route) { launchSingleTop = true }
                    },
                    backStackEntry = backStackEntry
                )

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavigationHost(navController, innerPadding, navigationType)
                }
            }

        }
        NavigationTypes.PERMANENT_NAVIGATION_DRAWER -> {
            NavDrawer(
                goHome = {
                    navController.popBackStack(NavigationRoutes.Home.name, inclusive = false)
                },
                navigate = {
                    route -> navController.navigate(route) { launchSingleTop = true }
                },
                backStackEntry = backStackEntry,
                content = {
                    Scaffold(
                        modifier = Modifier.fillMaxSize()
                    ) { innerPadding ->
                        NavigationHost(navController, innerPadding, navigationType)
                    }
                }
            )
        }
    }
}