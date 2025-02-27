package com.example.guessthepokemon.ui

import com.example.guessthepokemon.model.DifficultyModes
import com.example.guessthepokemon.model.Game
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GameTest {
    private lateinit var game: Game
    private val difficulty = DifficultyModes.Normal
    private val nameToGuess = "Pikachu"
    private val wrongGuess = "Squirtle"

    @Before
    fun initialize() {
        game = Game(difficulty, nameToGuess = nameToGuess)
    }

    @Test
    fun gameStartsWithCorrectDifficulty() {
        assertEquals(difficulty, game.difficulty)
    }

    @Test
    fun canPlaceGuess() {
        val beforeCount = game.guessCount

        game.placeGuess(wrongGuess)

        assertNotEquals(beforeCount, game.guessCount)
    }

    @Test
    fun displayImageCanBeTrue() {
        while (game.guessCount < difficulty.showImage) {
            game.placeGuess(wrongGuess)
        }

        assertTrue(game.displayImage())
    }

    @Test
    fun displayTypeOneCanBeTrue() {
        while (game.guessCount < difficulty.showTypeOne) {
            game.placeGuess(wrongGuess)
        }

        assertTrue(game.displayTypeOne())
    }

    @Test
    fun displayTypeTwoCanBeTrue() {
        while (game.guessCount < difficulty.showTypeTwo) {
            game.placeGuess(wrongGuess)
        }

        assertTrue(game.displayTypeTwo())
    }

    @Test
    fun displayGenerationCanBeTrue() {
        while (game.guessCount < difficulty.showGeneration) {
            game.placeGuess(wrongGuess)
        }

        assertTrue(game.displayGeneration())
    }

    @Test
    fun displayDexEntryOneCanBeTrue() {
        while (game.guessCount < difficulty.showDexEntryOne) {
            game.placeGuess(wrongGuess)
        }

        assertTrue(game.displayDexEntryOne())
    }

    @Test
    fun displayDexEntryTwoCanBeTrue() {
        while (game.guessCount < difficulty.showDexEntryTwo) {
            game.placeGuess(wrongGuess)
        }

        assertTrue(game.displayDexEntryTwo())
    }

    @Test
    fun playerCanWin() {
        game.placeGuess(nameToGuess)

        assertTrue(game.playerWon)
    }

    @Test
    fun playerCanWinOnFinalGuess() {
        while (game.guessesLeft > 1) {
            game.placeGuess(wrongGuess)
        }

        game.placeGuess(nameToGuess)

        assertTrue(game.playerWon)
    }

    @Test
    fun gameCanEndWithoutPlayerWinning() {
        while (game.guessesLeft > 0) {
            game.placeGuess(wrongGuess)
        }

        assertTrue(game.isFinished)
    }

    @Test
    fun playerCantWinAfterReachingMaxGuesses() {
        while (game.guessesLeft > 0) {
            game.placeGuess(wrongGuess)
        }

        game.placeGuess(nameToGuess)

        assertFalse(game.playerWon)
    }
}