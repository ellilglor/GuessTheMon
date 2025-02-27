package com.example.guessthepokemon.ui.pages.play.components.pokemon

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PokemonName(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        style = MaterialTheme.typography.headlineLarge,
        modifier = modifier.padding(bottom = 10.dp)
    )
}