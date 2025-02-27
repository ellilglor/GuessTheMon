package com.example.guessthepokemon.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * interface to interact with the roomDb.
 *
 * Inserting a pokemon will replace the
 * currently stored pokemon.
 */
@Dao
interface PokeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: DbPokemon)

    @Query("SELECT * FROM pokemon WHERE id = 1")
    fun getPokemon(): Flow<DbPokemon>
}