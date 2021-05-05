package com.jarroyo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class AppState {

    var currentScreen by mutableStateOf(CurrentScreen.HOME)
}

enum class CurrentScreen {
    HOME,
    DETAIL
}