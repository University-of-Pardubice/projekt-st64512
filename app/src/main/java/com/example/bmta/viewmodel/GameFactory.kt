package com.example.bmta.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GameFactory(
    private val bestScore : Int
) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Game::class.java)) {
            return Game(bestScore) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}