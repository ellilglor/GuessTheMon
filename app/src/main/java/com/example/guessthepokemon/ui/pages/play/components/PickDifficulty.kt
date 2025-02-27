package com.example.guessthepokemon.ui.pages.play.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.guessthepokemon.R
import com.example.guessthepokemon.model.DifficultyModes
import com.example.guessthepokemon.ui.navigation.NavigationTypes
import com.example.guessthepokemon.ui.pages.play.PlayViewModel
import com.example.guessthepokemon.ui.util.buttons.PickDifficultyButton

/**
 * First visible Composable of the playing screen.
 *
 * Lets you choose a difficulty to play.
 */
@Composable
fun PickDifficulty(
    viewModel: PlayViewModel,
    navigationType: NavigationTypes,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(R.string.select_difficulty),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
        fontStyle = FontStyle.Italic,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    )

    when(navigationType) {
        NavigationTypes.BOTTOM_NAVIGATION -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                enumValues<DifficultyModes>().forEach { difficulty ->
                    PickDifficultyButton(difficulty = difficulty, viewModel = viewModel)
                }
            }
        }
        else -> {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                enumValues<DifficultyModes>().forEach { difficulty ->
                    PickDifficultyButton(difficulty = difficulty, viewModel = viewModel)
                }
            }
        }

    }
}