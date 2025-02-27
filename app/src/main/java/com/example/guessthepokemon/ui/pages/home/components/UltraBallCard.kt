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
import com.example.guessthepokemon.data.UltraBallData
import com.example.guessthepokemon.model.DifficultyModes
import com.example.guessthepokemon.ui.theme.ultraBand
import com.example.guessthepokemon.ui.theme.ultraTop

/**
 * Card layout holding information about Hard difficulty.
 *
 * Made to look like a Ultra ball.
 *
 * @param onClick opens and closes the card.
 */
@Composable
fun UltraBallCard(
    onClick: (DifficultyModes) -> Unit,
    expanded: Boolean,
    modifier: Modifier = Modifier
) {
    val data = UltraBallData

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
                        .weight(0.1f)
                        .background(color = ultraTop)
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.15f)
                        .background(color = ultraBand)
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.5f)
                        .background(color = ultraTop)
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.15f)
                        .background(color = ultraBand)
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.1f)
                        .background(color = ultraTop)
                )
            }

            CardDesc(
                data = data,
                visible = expanded,
                modifier = Modifier.testTag(tag = stringResource(R.string.ultraball_desc_tag))
            )

            BottomHalf(onClick = { onClick(data.name) })
        },
        onClick = { onClick(data.name) },
        modifier = modifier.testTag(tag = stringResource(R.string.ultraball_tag))
    )
}