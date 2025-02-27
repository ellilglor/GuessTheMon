package com.example.guessthepokemon.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import com.example.guessthepokemon.R
import com.example.guessthepokemon.fake.FakeDataSource
import com.example.guessthepokemon.fake.FakePokeRepository
import com.example.guessthepokemon.model.DifficultyModes
import com.example.guessthepokemon.ui.navigation.NavigationTypes
import com.example.guessthepokemon.ui.pages.play.PlayScreen
import com.example.guessthepokemon.ui.pages.play.PlayViewModel
import com.example.guessthepokemon.ui.theme.GuessThePokemonTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GameTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val viewModel = PlayViewModel(pokeRepository = FakePokeRepository())

    @Before
    fun initialize() {
        composeTestRule.setContent {
            GuessThePokemonTheme {
                PlayScreen(viewModel = viewModel, navigationType = NavigationTypes.BOTTOM_NAVIGATION)
            }
        }
    }

    private fun placeGuess(input: String) {
        val tag = composeTestRule.activity.getString(R.string.input_tag)
        composeTestRule.onNodeWithTag(tag, useUnmergedTree = true).performTextInput(input)
        composeTestRule.onNodeWithTag(tag, useUnmergedTree = true).performImeAction()
    }

    @Test
    fun clickOnEasyButtonStartsEasyGame() {
        composeTestRule.onNodeWithText(DifficultyModes.Easy.name).performClick()
        composeTestRule.onNodeWithText(DifficultyModes.Easy.name, substring = true).assertIsDisplayed()
    }

    @Test
    fun clickOnNormalButtonStartsNormalGame() {
        composeTestRule.onNodeWithText(DifficultyModes.Normal.name).performClick()
        composeTestRule.onNodeWithText(DifficultyModes.Normal.name, substring = true).assertIsDisplayed()
    }

    @Test
    fun clickOnHardButtonStartsHardGame() {
        composeTestRule.onNodeWithText(DifficultyModes.Hard.name).performClick()
        composeTestRule.onNodeWithText(DifficultyModes.Hard.name, substring = true).assertIsDisplayed()
    }

    @Test
    fun imageCoverIsShownOnStart() {
        val desc = composeTestRule.activity.getString(R.string.poke_image_placeholder)
        composeTestRule.onNodeWithText(DifficultyModes.Easy.name).performClick()

        composeTestRule.onNodeWithContentDescription(desc).assertIsDisplayed()
    }

    @Test
    fun placingGuessUpdatesGuessesLeft() {
        val before = composeTestRule.onNodeWithText("Guesses", substring = true).toString()
        composeTestRule.onNodeWithText(DifficultyModes.Easy.name).performClick()

        placeGuess(FakeDataSource.pokemon2.name)

        composeTestRule.onNodeWithText(before).assertDoesNotExist()
    }

    @Test
    fun placingCorrectGuessFinishesGame() {
        val tag = composeTestRule.activity.getString(R.string.finished_tag)
        composeTestRule.onNodeWithText(DifficultyModes.Easy.name).performClick()

        placeGuess(FakeDataSource.pokemon1.name)

        composeTestRule.onNodeWithTag(tag).assertIsDisplayed()
    }

    @Test
    fun guessingMaxAmountOfGuessesFinishesGame() {
        val tag = composeTestRule.activity.getString(R.string.finished_tag)
        composeTestRule.onNodeWithText(DifficultyModes.Easy.name).performClick()

        while (viewModel.game.guessesLeft > 0) {
            placeGuess(FakeDataSource.pokemon2.name)
        }

        composeTestRule.onNodeWithTag(tag).assertIsDisplayed()
    }

    @Test
    fun gameCanBeRestarted() {
        val buttonText = composeTestRule.activity.getString(R.string.game_restart)
        val title = composeTestRule.activity.getString(R.string.select_difficulty)
        composeTestRule.onNodeWithText(DifficultyModes.Easy.name).performClick()
        placeGuess(FakeDataSource.pokemon1.name)

        composeTestRule.onNodeWithText(buttonText).performClick()

        composeTestRule.onNodeWithText(title).assertIsDisplayed()
    }

    @Test
    fun streakIsDisplayedAfterWinning() {
        val buttonText = composeTestRule.activity.getString(R.string.game_restart)
        val streak = composeTestRule.activity.getString(R.string.input_streak).replace("%1\$s", "")
        composeTestRule.onNodeWithText(DifficultyModes.Easy.name).performClick()
        placeGuess(FakeDataSource.pokemon1.name)

        composeTestRule.onNodeWithText(buttonText).performClick()
        composeTestRule.onNodeWithText(DifficultyModes.Easy.name).performClick()

        composeTestRule.onNodeWithText(streak, substring = true, useUnmergedTree = true).assertIsDisplayed()
    }
}