package com.example.guessthepokemon.model

data class PokemonInfo(
    val name: String,
    val generation: String,
    val dexEntries: List<String>
) {
    fun isValid(): Boolean = dexEntries.isNotEmpty() && listOf(name, generation).all { it.isNotEmpty() }
}

data class PokemonDetails(
    val types: List<String>,
    val sprite: String
) {
    fun isValid(): Boolean = types.isNotEmpty() && sprite.isNotEmpty()
}

data class Pokemon(
    val name: String,
    val generation: String,
    val dexEntries: List<String>,
    val types: List<String>,
    val sprite: String
)