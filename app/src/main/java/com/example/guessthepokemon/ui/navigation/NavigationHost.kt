package com.example.guessthepokemon.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.guessthepokemon.ui.pages.home.HomeScreen
import com.example.guessthepokemon.ui.pages.play.PlayScreen

@Composable
fun NavigationHost(
    navController: NavHostController,
    innerPadding: PaddingValues,
    navigationType: NavigationTypes
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.Home.name,
    ) {
        composable(NavigationRoutes.Home.name) {
            HomeScreen(modifier = Modifier.padding(innerPadding), navigationType = navigationType)
        }
        composable(NavigationRoutes.Play.name) {
            PlayScreen(modifier = Modifier.padding(innerPadding), navigationType = navigationType)
        }
    }
}