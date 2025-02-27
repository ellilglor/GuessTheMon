package com.example.guessthepokemon.ui.pages.play.components.pokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.example.guessthepokemon.R
import com.example.guessthepokemon.model.PokemonTypes
import com.example.guessthepokemon.ui.theme.typeSymbols

@Composable
fun PokemonType(
    type: String,
    modifier: Modifier = Modifier
) {
    val pokemonType = try {
        PokemonTypes.valueOf(type)
    } catch (_: IllegalArgumentException) {
        PokemonTypes.Unknown
    }

    val darkerColor = ColorUtils.blendARGB(
        Color.Black.toArgb(), pokemonType.color.toArgb(), 0.69f
    )

    Row(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .border(width = 1.dp, color = Color(darkerColor), shape = RoundedCornerShape(20.dp))
            .background(color = pokemonType.color, shape = RoundedCornerShape(20.dp)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(pokemonType.symbol),
            fontFamily = typeSymbols,
            color = Color.White,
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .offset(y = 3.dp)
        )

        Text(
            text = if (pokemonType == PokemonTypes.Unknown) stringResource(R.string.type_unknown) else type,
            color = Color.White,
            modifier = Modifier.padding(end = 15.dp, top = 1.dp, bottom = 1.dp)
        )
    }
}