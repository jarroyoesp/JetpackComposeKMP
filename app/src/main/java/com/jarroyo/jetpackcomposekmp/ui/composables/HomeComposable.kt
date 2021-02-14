package com.jarroyo.jetpackcomposekmp.ui.composables

import androidx.activity.ComponentActivity
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LifecycleOwnerAmbient
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jarroyo.jetpackcomposekmp.ui.AppState
import com.jarroyo.jetpackcomposekmp.ui.CurrentScreen
import com.jarroyo.jetpackcomposekmp.ui.viewModel.HomeViewModel
import com.jarroyo.jetpackcomposekmp.R
import com.jarroyo.jetpackcomposekmp.ui.utils.NetworkImage
import com.jarroyo.sharedcodeclient.domain.model.Breed

@Composable
fun HomeComposable(appState: AppState, homeViewModel: HomeViewModel) {
    val activity = (LifecycleOwnerAmbient.current as ComponentActivity)
    val animalList: List<Breed>? by homeViewModel.animalListLiveData.observeAsState()


    ScrollableColumn(modifier = Modifier.padding(16.dp)) {
        animalList?.let {
            for (animal in it) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = MaterialTheme.colors.surface,
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(16.dp),
                    ){
                        NetworkImage(
                            url = animal.image ?: "",
                            Modifier.fillMaxWidth().aspectRatio(2.0f),
                            circularRevealedEnabled = true
                        )
                        Column(
                            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                fontSize = 20.sp,
                                color = Color.Black, text = "${animal.name}",
                            )
                        }
                        Column(
                            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(
                                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                                onClick = { appState.currentScreen = CurrentScreen.DETAIL; appState.animalSelected = animal}, ) {
                                Text(text = "Detail")
                            }
                        }

                    }
                }
                Spacer(modifier = Modifier.fillMaxWidth().height(16.dp))
            }
        }
    }

    BackButtonHandler {
        activity.finish()
    }

}