package com.jarroyo.composables

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jarroyo.ui.NetworkImage
import com.jarroyo.ui.BottomBreedNavigation
import com.jarroyo.viewModel.HomeViewModel
import com.jarroyo.sharedcodeclient.domain.model.Breed

@Composable
fun HomeComposable(homeViewModel: HomeViewModel, breedClicked: (Breed) -> Unit = {}) {
    val animalList: List<Breed>? by homeViewModel.animalListLiveData.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                backgroundColor = Color.White,
                elevation = 8.dp,
                modifier = Modifier.padding(all = 5.dp),
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu"
                    )
                }
            )
        },
        content = {
            HomeScreenContent(homeViewModel, animalList, breedClicked)
        },
        bottomBar = {
            BottomBreedNavigation()
        }
    )
}

@Composable
fun HomeScreenContent(homeViewModel: HomeViewModel, animalList: List<Breed>?, breedClicked: (Breed) -> Unit = {}) {
    Column(
        modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        SearchBox(homeViewModel = homeViewModel)
        Spacer(modifier = Modifier.height(10.dp))
        BreedsList(animalList, breedClicked)
    }
}

@Composable
fun SearchBox(homeViewModel: HomeViewModel) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    Column {
        Text(
            text = "Find your breed",
            style = TextStyle(
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = searchText,
            onValueChange = {
                searchText = it
                homeViewModel.onSearchBreedText(it.text)
            },
            modifier = Modifier
                .offset(x = 20.dp)
                .fillMaxWidth(),
            placeholder = { Text("Search your breed") },
            shape = RoundedCornerShape(
                topStartPercent = 10,
                bottomStartPercent = 10
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search",
                    modifier = Modifier.padding(start = 10.dp)
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BreedsList(animalList: List<Breed>?, breedClicked: (Breed) -> Unit = {}) {
    Text(
        text = "Breeds",
        style = TextStyle(
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    )
    Spacer(modifier = Modifier.height(5.dp))
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp),
    ) {
        items(
            items = animalList ?: emptyList(),
            itemContent = { item ->
                BreedItem(item, breedClicked)
            }
        )
    }
   /* LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(
            items = animalList ?: emptyList(),
            itemContent = { item ->
                BreedItem(item, breedClicked)
            }
        )
    }*/
}

@SuppressLint("ResourceType")
@Composable
fun BreedItem(breed: Breed, breedClicked: (Breed) -> Unit = {}) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(15.dp)
            )
            .clickable {
                Log.d("BreedClicked", "Breed: ${breed} $breedClicked")
                breedClicked.invoke(breed)
            }
    ) {

        NetworkImage(
            url = breed.image ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.Center)
                .height(130.dp)
                .clip(RoundedCornerShape(15.dp)),
            circularRevealedEnabled = true,

        )

        Text(
            text = breed.name,
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(
                    color = Color(0xAA000000),
                    shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
                )
                .fillMaxWidth()
                .align(alignment = Alignment.BottomCenter)
                .padding(5.dp)
        )
    }
}