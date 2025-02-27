package com.example.guessthepokemon.model

sealed interface PokeRepoState {
    data class Success(val pokemon: Pokemon): PokeRepoState
    data object Error: PokeRepoState
    data object Loading: PokeRepoState
}