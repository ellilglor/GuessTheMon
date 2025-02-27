package com.example.guessthepokemon.ui.pages.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.guessthepokemon.R
import com.example.guessthepokemon.data.PokeBallData
import com.example.guessthepokemon.model.DifficultyModes
import com.example.guessthepokemon.ui.theme.pokeRed

/**
 * Card layout holding information about Easy difficulty.
 *
 * Made to look like a Pokéball.
 *
 * @param onClick opens and closes the card.
 */
@Composable
fun PokeBallCard(
    onClick: (DifficultyModes) -> Unit,
    expanded: Boolean,
    modifier: Modifier = Modifier
) {
    val data = PokeBallData

    CardTemplate(
        content = {
            Spacer(
                modifier = Modifier
                    .height(32.dp)
                    .fillMaxWidth()
                    .background(color = pokeRed)
            )

            CardDesc(
                data = data,
                visible = expanded,
                modifier = Modifier.testTag(tag = stringResource(R.string.pokeball_desc_tag))
            )

            BottomHalf(onClick = { onClick(data.name) })
        },
        onClick = { onClick(data.name) },
        modifier = modifier.testTag(tag = stringResource(R.string.pokeball_tag))
    )
}