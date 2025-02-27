package com.example.guessthepokemon.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.guessthepokemon.model.Pokemon

@Entity(tableName = "pokemon")
data class DbPokemon(
    @PrimaryKey
    val id: Int = 1,
    val name: String,
    val generation: String,
    val dexEntries: String,
    val types: String,
    val sprite: String
)

private const val SEPARATOR = "~~~"

fun Pokemon.asDbPokemon(): DbPokemon {
    return DbPokemon(
        name = name,
        generation = generation,
        dexEntries = dexEntries.joinToString(SEPARATOR),
        types = types.joinToString(SEPARATOR),
        sprite = sprite
    )
}

fun DbPokemon.asDomainPokemon(): Pokemon {
    return Pokemon(
        name = name,
        generation = generation,
        dexEntries = dexEntries.split(SEPARATOR),
        types = types.split(SEPARATOR),
        sprite = sprite
    )
}