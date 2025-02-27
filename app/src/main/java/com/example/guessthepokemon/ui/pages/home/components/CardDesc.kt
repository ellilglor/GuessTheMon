package com.example.guessthepokemon.ui.pages.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.guessthepokemon.R
import com.example.guessthepokemon.model.DifficultyInfo

/**
 * Composable holding information about the given difficulty.
 *
 * animated for a smoother transition.
 *
 * @param data information to be shown.
 */
@Composable
fun CardDesc(
    data: DifficultyInfo,
    visible: Boolean,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = visible,
        modifier = Modifier.background(color = Color.Black),
        enter = slideInVertically() + expandVertically(
            expandFrom = Alignment.Top
        ) + fadeIn(
            initialAlpha = 0.3f
        ),
        exit = fadeOut(animationSpec = tween(10)) + slideOutVertically() + shrinkVertically()
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(color = Color.Black)
                .padding(8.dp)
        ) {
            Column {
                Text(
                    text= stringResource(R.string.home_card_title, data.name),
                    style = MaterialTheme.typography.headlineMedium
                )

                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .padding(vertical = 5.dp),
                    thickness = 1.dp,
                    color = Color.White
                )
                
                for ((id, number) in data.info) {
                    Text(
                        text = "- ${stringResource(id = id, if (number > Int.MIN_VALUE) number else "")}",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}