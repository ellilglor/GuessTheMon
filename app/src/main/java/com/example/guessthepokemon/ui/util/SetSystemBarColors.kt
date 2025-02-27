package com.example.guessthepokemon.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import com.example.guessthepokemon.ui.navigation.NavigationTypes
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SetSystemBarColors(systemBarColor: Color, navBarColor: Color, navigationType: NavigationTypes) {
    val systemUiController = rememberSystemUiController()

    LaunchedEffect(key1 = navigationType) {
        when (navigationType) {
            NavigationTypes.BOTTOM_NAVIGATION -> {
                systemUiController.setSystemBarsColor(systemBarColor)
                systemUiController.setNavigationBarColor(navBarColor)
            }
            else -> systemUiController.setNavigationBarColor(Color.Black)
        }
    }
}