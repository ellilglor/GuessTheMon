package com.example.guessthepokemon.model

/**
 * All possible modes the game can be played in.
 *
 * Set any parameter to Int.MAX_VALUE to never be displayed
 * while playing.
 *
 * @param showImage guesses required to display the image
 * @param showGeneration guesses required to display the generation
 * @param showTypeOne guesses required to display the first type
 * @param showTypeTwo guesses required to display the second type
 * @param showDexEntryOne guesses required to display the first dex entry
 * @param showDexEntryTwo guesses required to display the second dex entry
 */
enum class DifficultyModes(
    val showImage: Int, val showGeneration: Int,
    val showTypeOne: Int, val showTypeTwo: Int,
    val showDexEntryOne: Int, val showDexEntryTwo: Int
) {
    Easy(
        showImage = 3, showGeneration = 0,
        showTypeOne = 0, showTypeTwo = 1,
        showDexEntryOne = 0, showDexEntryTwo = 2
    ),
    Normal(
        showImage = 4, showGeneration = 1,
        showTypeOne = 0, showTypeTwo = 3,
        showDexEntryOne = 2, showDexEntryTwo = 4
    ),
    Hard(
        showImage = Int.MAX_VALUE, showGeneration = 0,
        showTypeOne = 2, showTypeTwo = 4,
        showDexEntryOne = 3, showDexEntryTwo = Int.MAX_VALUE
    )
}