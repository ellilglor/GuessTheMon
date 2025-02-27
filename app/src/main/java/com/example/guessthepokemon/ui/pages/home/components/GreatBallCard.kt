package com.example.guessthepokemon.ui.pages.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.guessthepokemon.R
import com.example.guessthepokemon.data.GreatBallData
import com.example.guessthepokemon.model.DifficultyModes
import com.example.guessthepokemon.ui.theme.greatBand
import com.example.guessthepokemon.ui.theme.greatTop

/**
 * Card layout holding information about Normal difficulty.
 *
 * Made to look like a Great ball.
 *
 * @param onClick opens and closes the card.
 */
@Composable
fun GreatBallCard(
    onClick: (DifficultyModes) -> Unit,
    expanded: Boolean,
    modifier: Modifier = Modifier
) {
    val data = GreatBallData

    CardTemplate(
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.17f)
                        .background(color = greatBand)
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.66f)
                        .background(color = greatTop)
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.17f)
                        .background(color = greatBand)
                )
            }

            CardDesc(
                data = data,
                visible = expanded,
                modifier = Modifier.testTag(tag = stringResource(R.string.greatball_desc_tag))
            )

            BottomHalf(onClick = { onClick(data.name) })
        },
        onClick = { onClick(data.name) },
        modifier = modifier.testTag(tag = stringResource(R.string.greatball_tag))
    )
}
