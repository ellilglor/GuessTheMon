package com.example.guessthepokemon.ui.pages.play.components.pokemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.guessthepokemon.R
import com.example.guessthepokemon.ui.navigation.NavigationTypes
import com.example.guessthepokemon.ui.theme.typeSymbols

/**
 * Params while playing:
 * @param showFilter filter on the fetched image, changes based on
 * if cover is shown or not.
 * @param showCover Cover displayed over the image to hide it.
 */
@Composable
fun PokemonImage(
    url: String,
    name: String,
    modifier: Modifier = Modifier,
    showFilter: Boolean = false,
    showCover: Boolean = false,
    navigationType: NavigationTypes = NavigationTypes.BOTTOM_NAVIGATION,
) {
    val filter = when {
        showCover -> ColorFilter.tint(Color.Transparent)
        showFilter -> ColorFilter.tint(Color.Black)
        else -> null
    }

    Box(
        modifier = modifier.padding(bottom = 10.dp, top = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = url,
            placeholder = painterResource(id = R.drawable.missing_no),
            error = painterResource(id = R.drawable.missing_no),
            contentDescription = stringResource(R.string.poke_image_desc, name),
            modifier = Modifier.fillMaxSize(
                if (navigationType == NavigationTypes.BOTTOM_NAVIGATION) 0.66f else 0.99f
            ),
            colorFilter = filter
        )

        if (filter == null && url.contains("shiny")) {
            Text(
                fontFamily = typeSymbols,
                color = Color.Yellow,
                text = stringResource(R.string.poke_shiny_symbol),
                fontSize = 35.sp,
                modifier = Modifier.offset(x = 80.dp, y = (-80).dp)
            )
        }

        if (showCover) {
            Image(
                painter = painterResource(id = R.drawable.question),
                contentDescription = stringResource(R.string.poke_image_placeholder),
                modifier = Modifier.fillMaxWidth(
                    if (navigationType == NavigationTypes.BOTTOM_NAVIGATION) 0.44f else 0.56f
                )
            )
        }
    }
}
