package com.example.guessthepokemon.ui.util

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.guessthepokemon.R
import com.example.guessthepokemon.ui.theme.typeSymbols

val letters = mapOf(
    R.string.ball_symbol_one to Color.Black,
    R.string.ball_symbol_two to Color.Red,
    R.string.ball_symbol_three to Color.Gray,
    R.string.ball_symbol_four to Color.White
)

/**
 * With the correct font the letters display a pokéball
 */
@Composable
fun PokeballSymbol(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        for ((symbol, color) in letters) {
            Text(
                fontSize = 30.sp,
                text = stringResource(id = symbol),
                fontFamily = typeSymbols,
                color = color
            )
        }
    }
}