package com.example.guessthepokemon.network

import kotlinx.serialization.Serializable

/**
 * Used to get the available
 * amount of pokemon from the api.
 *
 * getRandom() generates a random numbers
 * between 1 & the available amount.
 */
@Serializable
data class ApiCount(
    private val count: Int
) {
    fun getRandom(): Int = (1..count).random()
}