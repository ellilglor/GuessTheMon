package com.example.guessthepokemon.ui.pages.play.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.guessthepokemon.R
import com.example.guessthepokemon.model.PokeRepoState
import com.example.guessthepokemon.model.PokemonTypes
import com.example.guessthepokemon.ui.navigation.NavigationTypes
import com.example.guessthepokemon.ui.pages.play.PlayViewModel
import com.example.guessthepokemon.ui.pages.play.components.other.ApiErrorMsg
import com.example.guessthepokemon.ui.pages.play.components.other.ApiLoadingMsg
import com.example.guessthepokemon.ui.pages.play.components.other.Input
import com.example.guessthepokemon.ui.pages.play.components.pokemon.DexEntry
import com.example.guessthepokemon.ui.pages.play.components.pokemon.Generation
import com.example.guessthepokemon.ui.pages.play.components.pokemon.PokemonImage
import com.example.guessthepokemon.ui.pages.play.components.pokemon.PokemonType

/**
 * Second visible Composable of the playing screen.
 *
 * Lets the user play a game and guess the fetched pokemon.
 *
 * "Recompose" is needed to update items inside the lazy column.
 *
 * @see PokemonImage How the image & cover is displayed.
 * @see PokemonType How type is displayed.
 * @see Generation How generation is displayed.
 * @see DexEntry How the dex entry is displayed.
 */
@Composable
fun Playing(
    viewModel: PlayViewModel,
    navigationType: NavigationTypes,
    modifier: Modifier = Modifier
) {
    when (val pokeRepoState = viewModel.pokeRepoState) {
        PokeRepoState.Loading -> {
            ApiLoadingMsg(text = R.string.game_setup)
        }
        is PokeRepoState.Success -> {
            val game = viewModel.game
            val pokemon = pokeRepoState.pokemon
            val recompose by viewModel.recompose.collectAsState()

            when (navigationType) {
                NavigationTypes.BOTTOM_NAVIGATION -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Input(
                            viewModel = viewModel,
                            game = game
                        )

                        LazyColumn(
                            modifier = modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            item(
                                key = recompose
                            ) {
                                PokemonImage(
                                    url = pokemon.sprite,
                                    name = pokemon.name,
                                    showFilter = true,
                                    showCover = !game.displayImage(),
                                    navigationType = navigationType,
                                )
                            }

                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier.padding(bottom = 10.dp)
                                ) {
                                    pokemon.types.forEachIndexed { index, type ->
                                        when {
                                            (index == 0 && game.displayTypeOne()) -> PokemonType(
                                                type = type
                                            )

                                            (index == 1 && game.displayTypeTwo()) -> PokemonType(
                                                type = type
                                            )

                                            else -> PokemonType(type = PokemonTypes.Unknown.name)
                                        }
                                    }
                                }
                            }

                            item {
                                Generation(
                                    generation = pokemon.generation,
                                    show = game.displayGeneration()
                                )
                            }

                            items(pokemon.dexEntries.size) { index ->
                                when (index) {
                                    0 -> DexEntry(
                                        entry = pokemon.dexEntries[index],
                                        visible = game.displayDexEntryOne()
                                    )

                                    1 -> DexEntry(
                                        entry = pokemon.dexEntries[index],
                                        visible = game.displayDexEntryTwo()
                                    )
                                }
                            }
                        }
                    }
                }
                else -> {
                    Row(
                        modifier = modifier.fillMaxSize()
                    ) {
                        LazyColumn(
                            modifier.fillMaxHeight().weight(0.5f).padding(vertical = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            item {
                                Input(
                                    viewModel = viewModel,
                                    game = game
                                )
                            }


                            item (
                                key = recompose
                            ) {
                                PokemonImage(
                                    url = pokemon.sprite,
                                    name = pokemon.name,
                                    showFilter = true,
                                    showCover = !game.displayImage(),
                                    navigationType = navigationType
                                )
                            }
                        }

                        LazyColumn(
                            modifier.fillMaxHeight().weight(0.5f).padding(vertical = 1.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            item(
                                key = recompose
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier.padding(bottom = 10.dp, top = 20.dp)
                                ) {
                                    pokemon.types.forEachIndexed { index, type ->
                                        when {
                                            (index == 0 && game.displayTypeOne()) -> PokemonType(type = type)
                                            (index == 1 && game.displayTypeTwo()) -> PokemonType(type = type)
                                            else -> PokemonType(type = PokemonTypes.Unknown.name)
                                        }
                                    }
                                }
                            }

                            item {
                                Generation(
                                    generation = pokemon.generation,
                                    show = game.displayGeneration(),
                                    modifier = Modifier.padding(bottom = 10.dp)
                                )
                            }

                            items(pokemon.dexEntries.size) {index ->
                                when (index) {
                                    0 -> DexEntry(entry = pokemon.dexEntries[index], visible = game.displayDexEntryOne())
                                    1 -> DexEntry(entry = pokemon.dexEntries[index], visible = game.displayDexEntryTwo())
                                }
                            }
                        }
                    }
                }
            }
        }
        PokeRepoState.Error -> {
            ApiErrorMsg(retry = { viewModel.resetGame() })
        }
    }
}
