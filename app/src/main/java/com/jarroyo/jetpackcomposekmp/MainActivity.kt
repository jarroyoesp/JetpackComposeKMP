package com.jarroyo.jetpackcomposekmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.jarroyo.jetpackcomposekmp.ui.AppState
import com.jarroyo.jetpackcomposekmp.ui.composables.HomeComposable
import com.jarroyo.jetpackcomposekmp.ui.composables.NavigateApp
import com.jarroyo.jetpackcomposekmp.ui.style.JetpackComposeKMPTheme
import com.jarroyo.jetpackcomposekmp.ui.viewModel.HomeViewModel

class MainActivity : ComponentActivity() {
    val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeKMPTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val appState = remember { AppState() }
                    NavigateApp(appState, homeViewModel)
                }
            }
        }
    }
}