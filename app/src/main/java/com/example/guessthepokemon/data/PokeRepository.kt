package com.example.guessthepokemon.data

import com.example.guessthepokemon.data.database.PokeDao
import com.example.guessthepokemon.data.database.asDbPokemon
import com.example.guessthepokemon.data.database.asDomainPokemon
import com.example.guessthepokemon.model.Pokemon
import com.example.guessthepokemon.network.PokeApiService
import com.example.guessthepokemon.network.asDomainObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty
import java.io.IOException

interface PokeRepository {
    suspend fun insert(item: Pokemon)
    fun getPokemon(): Flow<Pokemon>
    suspend fun refresh()
}

/**
 * Repository that fetches a pokemon from the api
 * and stores the data inside the database.
 */
class CachingPokeRepository(
    private val pokeDao: PokeDao,
    private val pokeApiService: PokeApiService
) : PokeRepository {
    override suspend fun insert(item: Pokemon) {
        pokeDao.insert(item.asDbPokemon())
    }

    override fun getPokemon(): Flow<Pokemon> {
        return pokeDao.getPokemon().map { it.asDomainPokemon() }.onEmpty { refresh() }
    }

    /**
     * Keep trying to fetch a pokemon from the api until
     * no field is empty.
     *
     * This can happen when new entries get added.
     *
     * Catches when there is no internet connection
     * so the game can be "played" with the previous entry.
     */
    override suspend fun refresh() {
        try {
            val maxId = pokeApiService.getPokemonCount()
            var valid = false

            while (!valid) {
                val randomId = maxId.getRandom()

                val info = pokeApiService.getPokemonInfo(randomId).asDomainObjects()
                if (!info.isValid()) continue

                val details = pokeApiService.getPokemonDetails(randomId).asDomainObjects()
                if (!details.isValid()) continue

                valid = true

                val pokemon = Pokemon(
                    name = info.name,
                    generation = info.generation,
                    dexEntries = info.dexEntries,
                    types = details.types,
                    sprite = details.sprite
                )

                insert(pokemon)
            }
        } catch (_: IOException) {

        }
    }
}
