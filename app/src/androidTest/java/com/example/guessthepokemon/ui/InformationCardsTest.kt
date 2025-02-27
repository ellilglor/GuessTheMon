package com.example.guessthepokemon.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.guessthepokemon.R
import com.example.guessthepokemon.ui.navigation.NavigationTypes
import com.example.guessthepokemon.ui.theme.GuessThePokemonTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InformationCardsTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun initialize() {
        composeTestRule.setContent {
            GuessThePokemonTheme {
                App(navigationType = NavigationTypes.BOTTOM_NAVIGATION)
            }
        }
    }

    @Test
    fun clickOnPokeballCardOpensCard() {
        val tag = composeTestRule.activity.getString(R.string.pokeball_tag)
        val descTag = composeTestRule.activity.getString(R.string.pokeball_desc_tag)

        composeTestRule.onNodeWithTag(tag).performClick()
        composeTestRule.onNodeWithTag(descTag, useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun clickOnGreatballCardOpensCard() {
        val tag = composeTestRule.activity.getString(R.string.greatball_tag)
        val descTag = composeTestRule.activity.getString(R.string.greatball_desc_tag)

        composeTestRule.onNodeWithTag(tag).performClick()
        composeTestRule.onNodeWithTag(descTag, useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun clickOnUltraballCardOpensCard() {
        val tag = composeTestRule.activity.getString(R.string.ultraball_tag)
        val descTag = composeTestRule.activity.getString(R.string.ultraball_desc_tag)

        composeTestRule.onNodeWithTag(tag).performClick()
        composeTestRule.onNodeWithTag(descTag, useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun clickOnClosedCardClosesOpenedCards() {
        val tag1 = composeTestRule.activity.getString(R.string.ultraball_tag)
        val tag2 = composeTestRule.activity.getString(R.string.greatball_tag)
        val descTag = composeTestRule.activity.getString(R.string.ultraball_desc_tag)

        composeTestRule.onNodeWithTag(tag1).performClick()
        composeTestRule.onNodeWithTag(tag2).performClick()

        composeTestRule.onNodeWithTag(descTag, useUnmergedTree = true).assertDoesNotExist()
    }
}