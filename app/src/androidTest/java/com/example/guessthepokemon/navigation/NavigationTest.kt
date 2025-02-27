package com.example.guessthepokemon.navigation

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.guessthepokemon.R
import com.example.guessthepokemon.ui.App
import com.example.guessthepokemon.ui.navigation.NavigationRoutes
import com.example.guessthepokemon.ui.navigation.NavigationTypes
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    private lateinit var navController: TestNavHostController

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun initialize() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            App(navController= navController, navigationType = NavigationTypes.BOTTOM_NAVIGATION)
        }
    }

    @Test
    fun verifyStartDestination() {
        assertEquals(NavigationRoutes.Home.name, navController.currentBackStackEntry?.destination?.route)
    }

    @Test
    fun clickOnPlayNavigatesToPlay() {
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.play_title)).performClick()
        assertEquals(NavigationRoutes.Play.name, navController.currentBackStackEntry?.destination?.route)
    }

    @Test
    fun canNavigateBackToHome() {
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.play_title)).performClick()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.home_title)).performClick()
        assertEquals(NavigationRoutes.Home.name, navController.currentBackStackEntry?.destination?.route)
    }

    @Test
    fun navigatingBackToHomeClearsBackstack() {
        val before = navController.backStack.last()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.play_title)).performClick()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.home_title)).performClick()
        assertEquals(before, navController.backStack.last())
    }
}