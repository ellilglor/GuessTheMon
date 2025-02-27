package com.example.guessthepokemon.ui

import com.example.guessthepokemon.model.DifficultyModes
import com.example.guessthepokemon.model.PokeGameState
import com.example.guessthepokemon.model.PokeRepoState
import com.example.guessthepokemon.ui.fake.FakeDataSource
import com.example.guessthepokemon.ui.fake.FakePokeRepository
import com.example.guessthepokemon.ui.pages.play.PlayViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class PlayViewModelTest {
    private lateinit var viewModel: PlayViewModel

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Before
    fun initialize() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = PlayViewModel(pokeRepository = FakePokeRepository())
    }

    @After
    fun close() {
        Dispatchers.resetMain()
    }

    @Test
    fun gameStateStartsInSelectingDifficultyState() {
        assertEquals(PokeGameState.SelectingDifficulty, viewModel.gameState)
    }

    @Test
    fun repoStateStartsInLoadingState() {
        assertEquals(PokeRepoState.Loading, viewModel.pokeRepoState)
    }

    @Test
    fun currentGuessStartsEmpty() {
        assertEquals("", viewModel.currentGuess)
    }

    @Test
    fun canStartGame() {
        viewModel.startGame(DifficultyModes.Normal)

        assertEquals(PokeGameState.Playing, viewModel.gameState)
    }

    @Test
    fun canStartEasyGame() {
        val difficulty = DifficultyModes.Easy

        viewModel.startGame(difficulty)

        assertEquals(difficulty, viewModel.game.difficulty)
    }

    @Test
    fun canStartNormalGame() {
        val difficulty = DifficultyModes.Normal

        viewModel.startGame(difficulty)

        assertEquals(difficulty, viewModel.game.difficulty)
    }

    @Test
    fun canStartHardGame() {
        val difficulty = DifficultyModes.Hard

        viewModel.startGame(difficulty)

        assertEquals(difficulty, viewModel.game.difficulty)
    }

    @Test
    fun canUpdateGuess() {
        val guess = "Pikachu"

        viewModel.updateGuess(guess)

        assertEquals(guess, viewModel.currentGuess)
    }

    @Test
    fun canPlaceGuess() {
        viewModel.startGame(DifficultyModes.Easy)
        val beforeCount = viewModel.game.guessCount

        viewModel.placeGuess()

        assertNotEquals(beforeCount, viewModel.game.guessCount)
    }

    @Test
    fun placingGuessResetsCurrentGuess() {
        val default = viewModel.currentGuess

        viewModel.updateGuess("Pikachu")
        viewModel.placeGuess()

        assertEquals(default, viewModel.currentGuess)
    }

    @Test
    fun gameStateCanChangeToFinished() {
        while (viewModel.game.guessesLeft > 0) {
            viewModel.placeGuess()
        }

        assertEquals(PokeGameState.Finished, viewModel.gameState)
    }

    @Test
    fun streakIncreasesByOneAfterWinning() {
        val before = viewModel.streak.value
        viewModel.updateGuess(FakeDataSource.pokemonInfo.name)

        viewModel.placeGuess()

        assertNotEquals(before, viewModel.streak.value)
    }

    @Test
    fun streakResetsAfterLosing() {
        val before = viewModel.streak.value
        viewModel.updateGuess(FakeDataSource.pokemonInfo.name)
        viewModel.placeGuess()

        viewModel.startGame(DifficultyModes.Normal)
        while (viewModel.game.guessesLeft > 0) {
            viewModel.placeGuess()
        }

        assertEquals(before, viewModel.streak.value)
    }

    @Test
    fun gameStateCanBeReset() {
        viewModel.startGame(DifficultyModes.Normal)

        viewModel.resetGame()

        assertEquals(PokeGameState.SelectingDifficulty, viewModel.gameState)
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class TestDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
): TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}