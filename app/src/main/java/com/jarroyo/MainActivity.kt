/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jarroyo

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.jarroyo.composables.BreedDetailsComposable
import com.jarroyo.composables.HomeComposable
import com.jarroyo.sharedcodeclient.domain.model.Breed
import com.jarroyo.ui.Screen
import com.jarroyo.ui.theme.MyTheme
import com.jarroyo.viewModel.HomeViewModel
import com.jarroyo.viewModel.NavigationViewModel

class MainActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModels()
    private val navigationViewModel: NavigationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                val currentScreen: Screen by navigationViewModel.currentScreen.observeAsState(Screen.BreedsList)
                MyApp(currentScreen = currentScreen,
                    homeViewModel = homeViewModel,
                    breedClicked = { breed ->
                        navigationViewModel.onBreedSelected(breed)
                    },
                    onBackPressed = {onBackPressed()})
            }
        }
        homeViewModel.getAnimalListFlow()
    }

    override fun onBackPressed() {
        if (navigationViewModel.onBackPressed()) {
            super.onBackPressed()
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(currentScreen: Screen = Screen.BreedsList,
          homeViewModel: HomeViewModel,
          breedClicked: (Breed) -> Unit = {},
          onBackPressed: () -> Unit) {

    Surface(color = MaterialTheme.colors.background) {
        when (currentScreen) {
            is Screen.BreedDetails -> BreedDetailsComposable((currentScreen as Screen.BreedDetails).breed) {
                onBackPressed.invoke()
            }
            is Screen.BreedsList -> HomeComposable(homeViewModel, breedClicked = breedClicked)
        }
    }
}
