package com.example.guessthepokemon.ui.navigation

import androidx.annotation.StringRes
import com.example.guessthepokemon.R

enum class NavigationRoutes(@StringRes val title: Int, @StringRes val symbol: Int) {
    Home(title = R.string.home_title, symbol = R.string.nav_home),
    Play(title = R.string.play_title, symbol = R.string.nav_play)
}