package com.example.guessthepokemon.ui.pages.play.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.guessthepokemon.R
import com.example.guessthepokemon.model.PokeRepoState
import com.example.guessthepokemon.ui.navigation.NavigationTypes
import com.example.guessthepokemon.ui.pages.play.PlayViewModel
import com.example.guessthepokemon.ui.pages.play.components.other.ApiErrorMsg
import com.example.guessthepokemon.ui.pages.play.components.other.ApiLoadingMsg
import com.example.guessthepokemon.ui.pages.play.components.pokemon.DexEntry
import com.example.guessthepokemon.ui.pages.play.components.pokemon.Generation
import com.example.guessthepokemon.ui.pages.play.components.pokemon.PokemonImage
import com.example.guessthepokemon.ui.pages.play.components.pokemon.PokemonName
import com.example.guessthepokemon.ui.pages.play.components.pokemon.PokemonType
import com.example.guessthepokemon.ui.util.buttons.RestartGameButton

/**
 * Third visible Composable of the playing screen.
 *
 * Shows if the user won or lost the game.
 *
 * Also contains a button to play again.
 * @see RestartGameButton
 */
@Composable
fun Finished(
    viewModel: PlayViewModel,
    navigationType: NavigationTypes,
    modifier: Modifier = Modifier
) {
    val game = viewModel.game
    val pokeApiState = viewModel.pokeRepoState
    val streak by viewModel.streak.collectAsState()

    val titleText = if (game.playerWon) {
        stringResource(R.string.game_won, game.difficulty) +
            when (game.guessCount) {
                1 -> stringResource(R.string.game_won_first_try)
                else -> stringResource(R.string.game_won_attempts, game.guessCount)
            } +
        stringResource(R.string.game_won_streak, streak)
    } else {
        stringResource(R.string.game_lost)
    }

    when (pokeApiState) {
        is PokeRepoState.Loading -> {
            ApiLoadingMsg(text = R.string.api_fetching)
        }
        is PokeRepoState.Success -> {
            val pokemon = pokeApiState.pokemon

            when (navigationType) {
                NavigationTypes.BOTTOM_NAVIGATION -> {
                    LazyColumn(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(top = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            Text(
                                text = titleText,
                                style = MaterialTheme.typography.titleLarge,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(bottom = 10.dp)
                                    .testTag(tag = stringResource(R.string.finished_tag))
                            )
                        }

                        item {
                            PokemonName(name = pokemon.name)
                        }

                        item {
                            PokemonImage(
                                url = pokemon.sprite,
                                name = pokemon.name,
                                navigationType = navigationType
                            )
                        }

                        item {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.padding(bottom = 10.dp)
                            ) {
                                pokemon.types.forEach { type ->
                                    PokemonType(type = type)
                                }
                            }
                        }

                        item {
                            Generation(generation = pokemon.generation)
                        }

                        item {
                            pokemon.dexEntries.forEach { entry ->
                                DexEntry(entry = entry, name = pokemon.name)
                            }
                        }

                        item {
                            RestartGameButton(text = stringResource(R.string.game_restart), onClick = { viewModel.resetGame() })
                        }
                    }
                }
                else -> {
                    Row(
                        modifier = modifier.fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier.fillMaxHeight().weight(0.5f).padding(vertical = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = titleText,
                                style = MaterialTheme.typography.titleLarge,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(bottom = 10.dp)
                                    .testTag(tag = stringResource(R.string.finished_tag))
                            )

                            RestartGameButton(text = stringResource(R.string.game_restart), onClick = { viewModel.resetGame() })
                        }
                        LazyColumn(
                            modifier = Modifier.fillMaxHeight().weight(0.5f).padding(vertical = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            item { PokemonName(name = pokemon.name) }

                            item { PokemonImage(
                                url = pokemon.sprite,
                                name = pokemon.name,
                                navigationType = navigationType
                            ) }

                            item {Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.padding(bottom = 10.dp)
                            ) {
                                pokemon.types.forEach { type ->
                                    PokemonType(type = type)
                                }
                            } }

                            item { Generation(generation = pokemon.generation) }

                            item { pokemon.dexEntries.forEach { entry ->
                                DexEntry(entry = entry, name = pokemon.name)
                            } }
                        }
                    }
                }
            }
        }
        is PokeRepoState.Error -> {
            ApiErrorMsg(retry = { viewModel.resetGame() })
        }
    }
}