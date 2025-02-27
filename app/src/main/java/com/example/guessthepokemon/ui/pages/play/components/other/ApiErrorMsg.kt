package com.example.guessthepokemon.ui.pages.play.components.other

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.guessthepokemon.R
import com.example.guessthepokemon.ui.util.buttons.RestartGameButton

@Composable
fun ApiErrorMsg(
    retry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.game_error_setup),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )

        RestartGameButton(text = stringResource(R.string.game_retry), onClick = retry)
    }
}