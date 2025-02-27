package com.example.guessthepokemon.ui.util.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.guessthepokemon.model.DifficultyModes
import com.example.guessthepokemon.ui.pages.play.PlayViewModel

@Composable
fun PickDifficultyButton(
    difficulty: DifficultyModes,
    viewModel: PlayViewModel,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { viewModel.startGame(difficulty) },
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        modifier = modifier.defaultMinSize(minWidth = 100.dp)
    ) {
        Text(
            text = difficulty.name,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium
        )
    }
}