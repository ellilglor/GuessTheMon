package com.example.guessthepokemon.network

import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {
    @GET("pokemon-species")
    suspend fun getPokemonCount(): ApiCount
    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(@Path("id") id: Int): ApiPokeDetails
    @GET("pokemon-species/{id}")
    suspend fun getPokemonInfo(@Path("id") id: Int): ApiPokeInfo
}

