package com.example.guessthepokemon.ui.pages.play.components.pokemon

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.guessthepokemon.ui.theme.dexFonts

@Composable
fun DexEntry(
    entry: String,
    modifier: Modifier = Modifier,
    name: String = "",
    visible: Boolean = true
) {
    AnimatedVisibility(
        visible = visible,
        enter =  fadeIn(initialAlpha = 0.3f)
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.tertiary),
            content = {
                Text(
                    text = if (name.isNotEmpty()) entry.replace("___", name) else entry,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White,
                    fontFamily = dexFonts,
                    modifier = Modifier.padding(16.dp)
                )
            }
        )
    }
}