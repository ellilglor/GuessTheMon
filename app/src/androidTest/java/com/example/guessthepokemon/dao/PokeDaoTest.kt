package com.example.guessthepokemon.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.guessthepokemon.data.database.PokeDao
import com.example.guessthepokemon.data.database.PokeDatabase
import com.example.guessthepokemon.data.database.asDbPokemon
import com.example.guessthepokemon.data.database.asDomainPokemon
import com.example.guessthepokemon.fake.FakeDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class PokeDaoTest {
    private lateinit var pokeDao: PokeDao
    private lateinit var pokeDb: PokeDatabase

    @Before
    fun initialize() {
        val context: Context = ApplicationProvider.getApplicationContext()

        pokeDb = Room.inMemoryDatabaseBuilder(context, PokeDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        pokeDao = pokeDb.pokeDao()
    }

    @After
    fun close() {
        pokeDb.close()
    }

    @Test
    fun insertPokemon() = runBlocking {
        pokeDao.insert(FakeDataSource.pokemon1.asDbPokemon())
        val pokemon = pokeDao.getPokemon().first()
        assertEquals(pokemon.asDomainPokemon(), FakeDataSource.pokemon1)
    }

    @Test
    fun insertingNewPokemonOverridesOld() = runBlocking {
        pokeDao.insert(FakeDataSource.pokemon1.asDbPokemon())
        val first = pokeDao.getPokemon().first()

        pokeDao.insert(FakeDataSource.pokemon2.asDbPokemon())
        val second = pokeDao.getPokemon().first()

        assertNotEquals(first, second)
    }
}