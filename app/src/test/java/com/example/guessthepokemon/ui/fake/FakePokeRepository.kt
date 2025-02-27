package com.example.guessthepokemon.ui.fake

import com.example.guessthepokemon.data.PokeRepository
import com.example.guessthepokemon.model.Pokemon

class FakePokeRepository: PokeRepository {
    override suspend fun insert(item: Pokemon) {
        // Insert
    }

    override fun getPokemon() = FakePokeApiService().getPokemonAsFlow()

    override suspend fun refresh() {
        FakePokeApiService().getPokemonAsFlow()
    }
}