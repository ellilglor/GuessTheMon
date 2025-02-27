package com.example.guessthepokemon.ui.fake

import com.example.guessthepokemon.model.Pokemon
import com.example.guessthepokemon.network.ApiCount
import com.example.guessthepokemon.network.PokeApiService
import com.example.guessthepokemon.network.asDomainObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePokeApiService: PokeApiService {
    override suspend fun getPokemonCount() = ApiCount(69)
    override suspend fun getPokemonDetails(id: Int) = FakeDataSource.pokemonDetails
    override suspend fun getPokemonInfo(id: Int) = FakeDataSource.pokemonInfo

    fun getPokemonAsFlow(): Flow<Pokemon> = flow {
        val info = getPokemonInfo(69).asDomainObjects()
        val details = getPokemonDetails(69).asDomainObjects()

        val pokemon = Pokemon(
            name = info.name,
            generation = info.generation,
            dexEntries = info.dexEntries,
            types = details.types,
            sprite = details.sprite
        )

        emit(pokemon)
    }
}