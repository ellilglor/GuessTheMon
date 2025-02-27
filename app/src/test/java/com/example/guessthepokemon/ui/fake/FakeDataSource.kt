package com.example.guessthepokemon.ui.fake

import com.example.guessthepokemon.model.PokemonTypes
import com.example.guessthepokemon.network.ApiPokeDetails
import com.example.guessthepokemon.network.ApiPokeInfo
import com.example.guessthepokemon.network.DexData
import com.example.guessthepokemon.network.GenerationData
import com.example.guessthepokemon.network.SpriteData
import com.example.guessthepokemon.network.TypeData

object FakeDataSource {
    val pokemonInfo = ApiPokeInfo(
        name = "MissingNo",
        generation = GenerationData("I"),
        dexEntries = listOf(
            DexData.FlavorText("First", DexData.Language("en")),
            DexData.FlavorText("Second", DexData.Language("en")),
        )
    )

    val pokemonDetails = ApiPokeDetails(
        types = listOf(
            TypeData.TypeEntry(TypeData.Type(PokemonTypes.Water.name)),
            TypeData.TypeEntry(TypeData.Type(PokemonTypes.Fire.name))
        ),
        sprite = SpriteData.Artwork(SpriteData.Official(SpriteData.SpriteUrl(
            "https://archives.bulbagarden.net/media/upload/b/b7/Missingno.png"
        )))
    )
}