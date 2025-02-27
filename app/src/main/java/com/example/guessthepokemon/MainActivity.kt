package com.example.guessthepokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.guessthepokemon.ui.App
import com.example.guessthepokemon.ui.navigation.NavigationTypes
import com.example.guessthepokemon.ui.theme.GuessThePokemonTheme
import com.example.guessthepokemon.ui.theme.bottomBarColor
import com.example.guessthepokemon.ui.util.SetSystemBarColors
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            lifecycleScope.launch {
                delay(250L)
            }
        }

        enableEdgeToEdge()

        setContent {
            GuessThePokemonTheme {
                val backgroundColor = MaterialTheme.colorScheme.background

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = backgroundColor
                ) {
                    val navigationType = when (calculateWindowSizeClass(activity = this).widthSizeClass) {
                        WindowWidthSizeClass.Compact -> NavigationTypes.BOTTOM_NAVIGATION
                        WindowWidthSizeClass.Medium -> NavigationTypes.NAVIGATION_RAIL
                        WindowWidthSizeClass.Expanded -> NavigationTypes.PERMANENT_NAVIGATION_DRAWER
                        else -> NavigationTypes.BOTTOM_NAVIGATION
                    }

                    SetSystemBarColors(
                        systemBarColor = backgroundColor,
                        navBarColor = bottomBarColor,
                        navigationType = navigationType
                    )

                    App(navigationType = navigationType)
                }
            }
        }
    }
}
