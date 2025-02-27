package com.example.guessthepokemon.model

/**
 * Game that enables the user to guess
 * the given Pokemon.
 *
 * Use the function to know when to display the
 * UI elements.
 *
 * Use placeGuess() to determine if the game finished
 * and whether or not the player won.
 *
 * The game is over when the player guessed the correct
 * name or ran out of guesses.
 */
data class Game(
    val difficulty: DifficultyModes = DifficultyModes.Easy,
    private val nameToGuess: String = "MissingNo",
    private val maxGuesses: Int = 5,
    private var _guessCount: Int = 0,
    private var _playerWon: Boolean = false,
) {
    val guessCount: Int get() = _guessCount
    val guessesLeft: Int get() = maxGuesses - _guessCount
    val playerWon: Boolean get() = _playerWon
    val isFinished: Boolean get() = _playerWon || (guessesLeft == 0)

    fun displayImage(): Boolean = _guessCount >= difficulty.showImage

    fun displayTypeOne(): Boolean = _guessCount >= difficulty.showTypeOne

    fun displayTypeTwo(): Boolean = _guessCount >= difficulty.showTypeTwo

    fun displayGeneration(): Boolean = _guessCount >= difficulty.showGeneration

    fun displayDexEntryOne(): Boolean = _guessCount >= difficulty.showDexEntryOne

    fun displayDexEntryTwo(): Boolean = _guessCount >= difficulty.showDexEntryTwo

    fun placeGuess(guess: String) {
        if (guess.equals(nameToGuess, ignoreCase = true) && guessesLeft > 0)
            _playerWon = true

        _guessCount += 1
    }
}