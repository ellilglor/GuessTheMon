package com.example.guessthepokemon.ui.pages.play.components.other

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.example.guessthepokemon.R
import com.example.guessthepokemon.model.Game
import com.example.guessthepokemon.ui.pages.play.PlayViewModel
import com.example.guessthepokemon.ui.util.PokeballSymbol
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Input used for guessing the pokemon during the game.
 *
 * You can only place a guess if the text is not empty.
 *
 * Hides the keyboard after the game reveals the second
 * dex entry because in some cases it can be hidden
 * behind it.
 */
@Composable
fun Input(
    viewModel: PlayViewModel,
    game: Game,
    modifier: Modifier = Modifier,
) {
    val composableScope = rememberCoroutineScope()
    val streak by viewModel.streak.collectAsState()
    val textFieldColor = MaterialTheme.colorScheme.primary
    var secondEntryIsShown by remember { mutableStateOf(false) }
    val softwareKeyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = viewModel.currentGuess,
        onValueChange = { viewModel.updateGuess(it) },
        modifier = modifier
            .width(250.dp)
            .padding(vertical = 5.dp)
            .testTag(stringResource(R.string.input_tag)),
        label = { Text(text = if (streak == 0) stringResource(R.string.input_place) else stringResource(
            R.string.input_streak, streak
        )
        ) },
        leadingIcon = { PokeballSymbol(modifier = Modifier.padding(start = 3.dp).offset(y = 4.dp)) },
        singleLine = true,
        supportingText = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.input_guesses, game.guessesLeft),
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = stringResource(R.string.input_mode, game.difficulty.name),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = textFieldColor,
            unfocusedLabelColor = textFieldColor,
            unfocusedSupportingTextColor = textFieldColor,
            focusedSupportingTextColor = textFieldColor
        ),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            autoCorrectEnabled = false,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                if (viewModel.currentGuess.isNotEmpty()) {
                    viewModel.placeGuess()

                    if (game.displayDexEntryTwo() && !secondEntryIsShown) {
                        secondEntryIsShown = true

                        composableScope.launch {
                            delay(200) // Delay needed to fix keyboard flickering
                            softwareKeyboardController?.hide()
                        }
                    }
                }
            }
        )
    )
}
