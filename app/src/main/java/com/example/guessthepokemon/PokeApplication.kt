package com.example.guessthepokemon

import android.app.Application
import com.example.guessthepokemon.data.AppContainer
import com.example.guessthepokemon.data.DefaultAppContainer

class PokeApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(applicationContext)
    }
}