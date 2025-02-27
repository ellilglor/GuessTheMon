package com.example.guessthepokemon.network

import com.example.guessthepokemon.model.PokemonDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Locale
import kotlin.random.Random

@Serializable
data class ApiPokeDetails(
    val types: List<TypeData.TypeEntry>,
    @SerialName("sprites")
    val sprite: SpriteData.Artwork
)

class TypeData {
    @Serializable
    data class TypeEntry(
        val type: Type
    )
    @Serializable
    data class Type(
        val name: String
    )
}

class SpriteData {
    @Serializable
    data class Artwork(
        @SerialName("other")
        val value: Official
    )

    @Serializable
    data class Official(
        @SerialName("official-artwork")
        val artwork: SpriteUrl
    )

    @Serializable
    data class SpriteUrl(
        @SerialName("front_default")
        val regular: String = "",
        @SerialName("front_shiny")
        val shiny: String = ""
    )
}

/**
 * Transforms the Api object into a domain usable version.
 *
 * Makes each type's first letter title-case.
 *
 * has a 1/20 chance to return a shiny sprite instead
 * of a regular one.
 */
fun ApiPokeDetails.asDomainObjects(): PokemonDetails {
    val isShiny = Random.nextInt(1, 21) == 20

    return PokemonDetails(
        types = types.map { it -> it.type.name.replaceFirstChar { it.titlecase(Locale.ROOT) } },
        sprite = if (isShiny) sprite.value.artwork.shiny else sprite.value.artwork.regular
    )
}