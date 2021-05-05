package com.jarroyo.composables

import androidx.compose.runtime.Composable
import com.jarroyo.AppState
import com.jarroyo.CurrentScreen
import com.jarroyo.viewModel.HomeViewModel

@Composable
fun NavigateApp(appState: AppState, homeViewModel: HomeViewModel) {
    // Choose which screen to show based on the value in the currentScreen variable inside AppState
    when (appState.currentScreen) {
        CurrentScreen.HOME -> HomeComposable(homeViewModel)
        //CurrentScreen.DETAIL -> DetailComposable(appState, homeViewModel)
    }
}