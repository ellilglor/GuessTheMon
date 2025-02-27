package com.example.guessthepokemon.ui.layout

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import com.example.guessthepokemon.ui.navigation.NavigationRoutes
import com.example.guessthepokemon.ui.theme.bottomBarColor
import com.example.guessthepokemon.ui.theme.typeSymbols

/**
 * Navigation system used for expanded mode.
 *
 * @param backStackEntry is used for highlighting current page.
 */
@Composable
fun NavDrawer(
    goHome: () -> Unit,
    navigate: (String) -> Unit,
    backStackEntry: NavBackStackEntry?,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    PermanentNavigationDrawer(
        drawerContent = {
            PermanentDrawerSheet(
                modifier = modifier.width(140.dp),
                drawerContainerColor = bottomBarColor
            ) {
                Spacer(modifier = Modifier.height(10.dp))

                enumValues<NavigationRoutes>().forEach { route ->
                    val isCurrentPage = backStackEntry?.destination?.route == route.name

                    NavigationDrawerItem(
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedContainerColor = bottomBarColor
                        ),
                        shape = RoundedCornerShape(0.dp),
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
                                text = stringResource(route.title)
                            )
                        }
                    )
                }
            }
        }
    ) {
        content()
    }
}