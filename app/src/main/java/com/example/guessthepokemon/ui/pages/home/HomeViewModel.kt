package com.example.guessthepokemon.ui.pages.home

import androidx.lifecycle.ViewModel
import com.example.guessthepokemon.model.DifficultyModes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * viewModel holding state of all information cards.
 */
class HomeViewModel : ViewModel() {
    private val _isPokeBallExpanded = MutableStateFlow(false)
    val isPokeBallExpanded = _isPokeBallExpanded.asStateFlow()

    private val _isGreatBallExpanded = MutableStateFlow(false)
    val isGreatBallExpanded = _isGreatBallExpanded.asStateFlow()

    private val _isUltraBallExpanded = MutableStateFlow(false)
    val isUltraBallExpanded = _isUltraBallExpanded.asStateFlow()

    fun toggleCardState(difficulty: DifficultyModes) {
        when (difficulty) {
            DifficultyModes.Easy -> _isPokeBallExpanded.value = !_isPokeBallExpanded.value
            DifficultyModes.Normal -> _isGreatBallExpanded.value = !_isGreatBallExpanded.value
            DifficultyModes.Hard -> _isUltraBallExpanded.value = !_isUltraBallExpanded.value
        }

        closeCards(difficulty)
    }

    /**
     *  Closes other open cards so only one can be open
     */
    private fun closeCards(difficulty: DifficultyModes) {
        when (difficulty) {
            DifficultyModes.Easy -> {
                if (_isGreatBallExpanded.value) _isGreatBallExpanded.value = false
                if (_isUltraBallExpanded.value) _isUltraBallExpanded.value = false
            }
            DifficultyModes.Normal -> {
                if (_isPokeBallExpanded.value) _isPokeBallExpanded.value = false
                if (_isUltraBallExpanded.value) _isUltraBallExpanded.value = false
            }
            DifficultyModes.Hard -> {
                if (_isPokeBallExpanded.value) _isPokeBallExpanded.value = false
                if (_isGreatBallExpanded.value) _isGreatBallExpanded.value = false
            }
        }
    }
}
