package com.jarroyo.jetpackcomposekmp.ui.composables

import androidx.compose.runtime.Composable
import com.jarroyo.jetpackcomposekmp.ui.AppState
import com.jarroyo.jetpackcomposekmp.ui.CurrentScreen
import com.jarroyo.jetpackcomposekmp.ui.viewModel.HomeViewModel

@Composable
fun NavigateApp(appState: AppState, homeViewModel: HomeViewModel) {
    // Choose which screen to show based on the value in the currentScreen variable inside AppState
    when (appState.currentScreen) {
        CurrentScreen.HOME -> HomeComposable(appState, homeViewModel)
        CurrentScreen.DETAIL -> DetailComposable(appState, homeViewModel)
    }
}