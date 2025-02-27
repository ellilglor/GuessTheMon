package com.example.guessthepokemon.ui.pages.play.components.pokemon

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.guessthepokemon.R

@Composable
fun Generation(generation: String, modifier: Modifier = Modifier, show: Boolean = true) {
    Text(
        text = stringResource(R.string.poke_generation, if (show) generation else "?"),
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
    )
}