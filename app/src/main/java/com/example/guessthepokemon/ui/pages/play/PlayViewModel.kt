package com.example.guessthepokemon.ui.pages.play

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.guessthepokemon.PokeApplication
import com.example.guessthepokemon.data.PokeRepository
import com.example.guessthepokemon.model.DifficultyModes
import com.example.guessthepokemon.model.Game
import com.example.guessthepokemon.model.PokeGameState
import com.example.guessthepokemon.model.PokeRepoState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * viewModel holding everything related to the game.
 *
 * @see Game for more info about how the game works.
 * @see PokeRepository to see how fetching a pokemon works.
 */
class PlayViewModel(
    private val pokeRepository: PokeRepository
) : ViewModel() {
    var pokeRepoState: PokeRepoState by mutableStateOf(PokeRepoState.Loading)
        private set
    var gameState: PokeGameState by mutableStateOf(PokeGameState.SelectingDifficulty)
        private set
    var game: Game by mutableStateOf(Game())
        private set
    var currentGuess: String by mutableStateOf("")
        private set

    private val _streak = MutableStateFlow(0)
    val streak = _streak.asStateFlow()

    /**
     * Value used to force recompose items in the lazy column during the game
     */
    private val _recompose = MutableStateFlow(true)
    val recompose = _recompose.asStateFlow()

    fun updateGuess(input: String) {
        currentGuess = input
    }

    fun placeGuess() {
        game.placeGuess(currentGuess)
        updateGuess("")
        _recompose.value = !_recompose.value

        if (game.isFinished) {
            _streak.value = if (game.playerWon) _streak.value + 1 else 0

            gameState = PokeGameState.Finished
        }
    }

    fun startGame(difficulty: DifficultyModes) {
        gameState = PokeGameState.Playing

        getRepoPokemon(difficulty)
    }

    fun resetGame() {
        gameState = PokeGameState.SelectingDifficulty
    }

    private var getPokemonJob: Job? = null

    private fun getRepoPokemon(difficulty: DifficultyModes) {
        pokeRepoState = PokeRepoState.Loading

        getPokemonJob?.cancel()

        getPokemonJob = viewModelScope.launch {
            try {
                pokeRepository.refresh()

                pokeRepository.getPokemon()
                    .collectLatest {
                        game = Game(difficulty = difficulty, nameToGuess = it.name)
                        pokeRepoState = PokeRepoState.Success(it)
                    }
            } catch (e: IOException) {
                pokeRepoState = PokeRepoState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as PokeApplication
                val pokeRepository = application.container.pokeRepository
                PlayViewModel(pokeRepository)
            }
        }
    }
}