package com.example.guessthepokemon.ui

import com.example.guessthepokemon.model.DifficultyModes
import com.example.guessthepokemon.ui.pages.home.HomeViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeViewModelTest {
    private val viewModel = HomeViewModel()

    @Test
    fun viewModelStartsWithClosedPokeBallCard() {
        assertEquals(false, viewModel.isPokeBallExpanded.value)
    }

    @Test
    fun viewModelStartsWithClosedGreatBallCard() {
        assertEquals(false, viewModel.isGreatBallExpanded.value)
    }

    @Test
    fun viewModelStartsWithClosedUltraBallCard() {
        assertEquals(false, viewModel.isUltraBallExpanded.value)
    }

    @Test
    fun viewModelCanOpenPokeBallCard() {
        viewModel.toggleCardState(DifficultyModes.Easy)
        assertEquals(true, viewModel.isPokeBallExpanded.value)
    }

    @Test
    fun viewModelCanOpenGreatBallCard() {
        viewModel.toggleCardState(DifficultyModes.Normal)
        assertEquals(true, viewModel.isGreatBallExpanded.value)
    }

    @Test
    fun viewModelCanOpenUltraBallCard() {
        viewModel.toggleCardState(DifficultyModes.Hard)
        assertEquals(true, viewModel.isUltraBallExpanded.value)
    }

    @Test
    fun viewModelCanToggleCardExpandedState() {
        viewModel.toggleCardState(DifficultyModes.Easy)
        viewModel.toggleCardState(DifficultyModes.Easy)
        assertEquals(false, viewModel.isPokeBallExpanded.value)
    }

    @Test
    fun viewModelClosesOpenedCardsWhenOpeningOtherCard() {
        viewModel.toggleCardState(DifficultyModes.Easy)
        viewModel.toggleCardState(DifficultyModes.Normal)
        assertEquals(false, viewModel.isPokeBallExpanded.value)
    }
}