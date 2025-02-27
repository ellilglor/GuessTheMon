package com.example.guessthepokemon.ui.pages.play

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.guessthepokemon.model.PokeGameState
import com.example.guessthepokemon.ui.navigation.NavigationTypes
import com.example.guessthepokemon.ui.pages.play.components.Finished
import com.example.guessthepokemon.ui.pages.play.components.PickDifficulty
import com.example.guessthepokemon.ui.pages.play.components.Playing

/**
 * Screen where the game can be played on.
 *
 * Initial state shows buttons to select
 * a difficulty and start a game.
 */
@Composable
fun PlayScreen(
    navigationType: NavigationTypes,
    modifier: Modifier = Modifier,
    viewModel: PlayViewModel = viewModel(factory = PlayViewModel.Factory)
) {
    Box(modifier = modifier) {
        when (viewModel.gameState) {
            is PokeGameState.SelectingDifficulty -> {
                PickDifficulty(viewModel = viewModel, navigationType = navigationType)
            }
            is PokeGameState.Playing -> {
                Playing(viewModel = viewModel, navigationType = navigationType)
            }
            is PokeGameState.Finished -> {
                Finished(viewModel = viewModel, navigationType = navigationType)
            }
        }
    }
}
