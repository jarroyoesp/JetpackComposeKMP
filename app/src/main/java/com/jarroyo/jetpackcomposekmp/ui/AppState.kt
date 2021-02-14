package com.jarroyo.jetpackcomposekmp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.jarroyo.sharedcodeclient.domain.model.Breed

class AppState {

    var currentScreen by mutableStateOf(CurrentScreen.HOME)
    var animalSelected by mutableStateOf(Breed("unknown"))
}

enum class CurrentScreen {
    HOME,
    DETAIL
}