package com.example.guessthepokemon.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.example.guessthepokemon.R

/**
 * Class that holds all symbols & colors for
 * each (currently available) pokemon type.
 *
 * By using the correct font the letter turn into
 * symbol related to the type.
 */
enum class PokemonTypes(@StringRes val symbol: Int, val color: Color) {
    Normal(symbol = R.string.poke_type_normal, color = Color(0xffaab09f)),
    Fire(symbol = R.string.poke_type_fire, color = Color(0xffea7a3c)),
    Fighting(symbol = R.string.poke_type_fighting, color = Color(0xffcb5f48)),
    Water(symbol = R.string.poke_type_water, color = Color(0xff539ae2)),
    Flying(symbol = R.string.poke_type_flying, color = Color(0xff7da6de)),
    Grass(symbol = R.string.poke_type_grass, color = Color(0xff71c558)),
    Poison(symbol = R.string.poke_type_poison, color = Color(0xffb468b7)),
    Electric(symbol = R.string.poke_type_electric, color = Color(0xffe5c531)),
    Ground(symbol = R.string.poke_type_ground, color = Color(0xffcc9f4f)),
    Psychic(symbol = R.string.poke_type_psychic, color = Color(0xffe5709b)),
    Rock(symbol = R.string.poke_type_rock, color = Color(0xffb2a061)),
    Ice(symbol = R.string.poke_type_ice, color = Color(0xff70cbd4)),
    Bug(symbol = R.string.poke_type_bug, color = Color(0xff94bc4a)),
    Dragon(symbol = R.string.poke_type_dragon, color = Color(0xff6a7baf)),
    Ghost(symbol = R.string.poke_type_ghost, color = Color(0xff846ab6)),
    Dark(symbol = R.string.poke_type_dark, color = Color(0xff736c75)),
    Steel(symbol = R.string.poke_type_steel, color = Color(0xff89a1b0)),
    Fairy(symbol = R.string.poke_type_fairy, color = Color(0xffe397d1)),
    Unknown(symbol = R.string.poke_type_unknown, color = Color(0xff68A090))
}