package com.example.guessthepokemon.ui.pages.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.guessthepokemon.ui.theme.pokeBlack
import com.example.guessthepokemon.ui.theme.pokeWhite

@Composable
fun BottomHalf(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (button, ball) = createRefs()

        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .constrainAs(ball) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Column {
                Spacer(
                    modifier = Modifier
                        .fillMaxHeight(0.4f)
                        .fillMaxWidth()
                        .background(color = pokeBlack)
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = pokeWhite)
                )
            }

        }

        OutlinedButton(
            onClick = { onClick() },
            modifier= Modifier
                .size(40.dp)
                .offset(y = (-15).dp)
                .constrainAs(button) {
                    top.linkTo(ball.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            shape = CircleShape,
            border= BorderStroke(4.dp, pokeBlack),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor =  pokeBlack,
                containerColor = pokeWhite
            )
        ) {

        }
    }
}