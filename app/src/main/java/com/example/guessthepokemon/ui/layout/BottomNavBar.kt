package com.example.guessthepokemon.ui.layout

import androidx.compose.foundation.layout.offset
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import com.example.guessthepokemon.ui.navigation.NavigationRoutes
import com.example.guessthepokemon.ui.theme.typeSymbols

/**
 * Navigation system used for compact mode.
 *
 * @param backStackEntry is used for highlighting current page.
 */
@Composable
fun BottomNavBar(
    goHome: () -> Unit,
    navigate: (String) -> Unit,
    backStackEntry: NavBackStackEntry?,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        enumValues<NavigationRoutes>().forEach { route ->
            val isCurrentPage = backStackEntry?.destination?.route == route.name

            NavigationBarItem(
                selected = isCurrentPage,
                onClick = {
                    when (route) {
                        NavigationRoutes.Home -> goHome()
                        else -> navigate(route.name)
                    }
                },
                icon = {
                    Text(
                        text = stringResource(route.symbol),
                        fontFamily = typeSymbols,
                        fontSize = 26.sp,
                        fontWeight = if (isCurrentPage) FontWeight.Bold else FontWeight.Normal,
                        modifier = Modifier.offset(y = 1.dp)
                    )
                },
                label = {
                    Text(
                        text = stringResource(route.title),
                        modifier = Modifier.offset(y = 5.dp)
                    )
                }
            )
        }
    }
}
