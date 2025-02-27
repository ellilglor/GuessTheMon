package com.example.guessthepokemon.network

import com.example.guessthepokemon.model.PokemonInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Locale
import kotlin.random.Random

@Serializable
data class ApiPokeInfo(
    val name: String,
    val generation: GenerationData,
    @SerialName("flavor_text_entries")
    val dexEntries: List<DexData.FlavorText>
)

@Serializable
data class GenerationData(
    @SerialName("name")
    val name: String
)

class DexData {
    @Serializable
    data class FlavorText(
        @SerialName("flavor_text")
        val entry: String,
        val language: Language
    )

    @Serializable
    data class Language(
        val name: String
    )
}

/**
 * Transforms the Api object into a domain usable version.
 *
 * Makes the first letter of the name title-case.
 *
 * Cleans up the generation by removing text.
 *
 * Filters for only English dex entries.
 * Cleans them up & makes them unique.
 * Randomizes them & takes 2.
 */
fun ApiPokeInfo.asDomainObjects() = PokemonInfo(
    name = name.replaceFirstChar { it.titlecase(Locale.ROOT) },
    generation = generation.name.removePrefix("generation-").uppercase(),
    dexEntries = dexEntries
        .filter { it.language.name == "en" }
        .map { it.entry
            .replace(name, "___", true)
            .replace("POKéMON", "Pokémon", true)
            .replace("\u000c", " ")
            .replace("\n", " ")
        }
        .toSet()
        .shuffled(Random.Default)
        .take(2)
)