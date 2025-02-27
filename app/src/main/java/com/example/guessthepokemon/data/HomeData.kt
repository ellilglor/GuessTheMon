package com.example.guessthepokemon.data

import com.example.guessthepokemon.R
import com.example.guessthepokemon.model.DifficultyInfo
import com.example.guessthepokemon.model.DifficultyModes

val PokeBallData = DifficultyInfo(
    name = DifficultyModes.Easy,
    info = mapOf(
        R.string.easy_one to DifficultyModes.Easy.showImage,
        R.string.easy_two to Int.MIN_VALUE,
        R.string.easy_three to DifficultyModes.Easy.showTypeTwo,
        R.string.easy_four to DifficultyModes.Easy.showDexEntryTwo
    )
)

val GreatBallData = DifficultyInfo(
    name = DifficultyModes.Normal,
    info = mapOf(
        R.string.normal_one to DifficultyModes.Normal.showImage,
        R.string.normal_two to DifficultyModes.Normal.showGeneration,
        R.string.normal_three to DifficultyModes.Normal.showTypeTwo,
        R.string.normal_four to DifficultyModes.Normal.showDexEntryTwo
    )
)

val UltraBallData = DifficultyInfo(
    name = DifficultyModes.Hard,
    info = mapOf(
        R.string.hard_one to Int.MIN_VALUE,
        R.string.hard_two to DifficultyModes.Hard.showTypeOne,
        R.string.hard_three to DifficultyModes.Hard.showTypeTwo,
        R.string.hard_four to DifficultyModes.Hard.showDexEntryOne
    )
)