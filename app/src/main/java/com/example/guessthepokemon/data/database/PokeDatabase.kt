package com.example.guessthepokemon.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DbPokemon::class], version = 1)
abstract class PokeDatabase: RoomDatabase() {
    abstract fun pokeDao(): PokeDao
}