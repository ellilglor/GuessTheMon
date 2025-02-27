package com.example.guessthepokemon.ui.pages.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.guessthepokemon.R

@Composable
fun Welcome(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.home_welcome),
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
    )
}