package com.example.guessthepokemon.model

/**
 * Used to display information about
 * a difficulty on the home screen.
 *
 * @param info set second Int to Int.MIN_VALUE if no parameter
 * needs to be given to StringResource
 */
data class DifficultyInfo(
    val name: DifficultyModes,
    val info: Map<Int, Int>
)