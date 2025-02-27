package com.example.guessthepokemon.data

import android.content.Context
import androidx.room.Room
import com.example.guessthepokemon.data.database.PokeDao
import com.example.guessthepokemon.data.database.PokeDatabase
import com.example.guessthepokemon.network.PokeApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val pokeRepository: PokeRepository
}

class DefaultAppContainer(
    private val applicationContext: Context
): AppContainer {

    private val json = Json { ignoreUnknownKeys = true; coerceInputValues = true }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(
            json.asConverterFactory("application/json".toMediaType())
        )
        .baseUrl("https://pokeapi.co/api/v2/")
        .build()


    private val pokeService: PokeApiService by lazy {
        retrofit.create(PokeApiService::class.java)
    }

    private val pokeDb: PokeDatabase by lazy {
        Room.databaseBuilder(applicationContext, PokeDatabase::class.java, "poke_database")
            .build()
    }

    private val pokeDao: PokeDao by lazy {
        pokeDb.pokeDao()
    }

    override val pokeRepository: PokeRepository by lazy {
        CachingPokeRepository(pokeDao = pokeDao, pokeApiService = pokeService)
    }
}