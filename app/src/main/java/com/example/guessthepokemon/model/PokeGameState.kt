package com.example.guessthepokemon.model

sealed interface PokeGameState {
    data object SelectingDifficulty: PokeGameState
    data object Playing: PokeGameState
    data object Finished : PokeGameState
}