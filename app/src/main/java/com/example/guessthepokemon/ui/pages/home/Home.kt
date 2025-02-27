package com.example.guessthepokemon.ui.pages.home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.guessthepokemon.ui.navigation.NavigationTypes
import com.example.guessthepokemon.ui.pages.home.components.GreatBallCard
import com.example.guessthepokemon.ui.pages.home.components.PokeBallCard
import com.example.guessthepokemon.ui.pages.home.components.UltraBallCard
import com.example.guessthepokemon.ui.pages.home.components.Welcome

/**
 * First visible screen off the app.
 *
 * Shows cards holding information about each difficulty.
 */
@Composable
fun HomeScreen(
    navigationType: NavigationTypes,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val pokeExpanded by viewModel.isPokeBallExpanded.collectAsState()
    val greatExpanded by viewModel.isGreatBallExpanded.collectAsState()
    val ultraExpanded by viewModel.isUltraBallExpanded.collectAsState()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        item { Welcome() }

        when (navigationType) {
            NavigationTypes.BOTTOM_NAVIGATION -> {
                val mod = Modifier.padding(horizontal = 16.dp)

                item { PokeBallCard(
                    expanded = pokeExpanded,
                    onClick = { card -> viewModel.toggleCardState(card)},
                    modifier = mod
                ) }
                item { GreatBallCard(
                    expanded = greatExpanded,
                    onClick = { card -> viewModel.toggleCardState(card)},
                    modifier = mod
                ) }
                item { UltraBallCard(
                    expanded = ultraExpanded,
                    onClick = { card -> viewModel.toggleCardState(card)},
                    modifier = mod
                ) }
            }
            else -> {
                val mod = Modifier.animateContentSize()

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth().height(350.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        PokeBallCard(
                            expanded = pokeExpanded,
                            onClick = { card -> viewModel.toggleCardState(card)},
                            modifier = mod.weight(if (pokeExpanded) 1f else 0.33f)
                        )
                        GreatBallCard(
                            expanded = greatExpanded,
                            onClick = { card -> viewModel.toggleCardState(card)},
                            modifier = mod.weight(if (greatExpanded) 1f else 0.33f)
                        )
                        UltraBallCard(
                            expanded = ultraExpanded,
                            onClick = { card -> viewModel.toggleCardState(card)},
                            modifier = mod.weight(if (ultraExpanded) 1f else 0.33f)
                        )
                    }
                }
            }
        }
    }
}