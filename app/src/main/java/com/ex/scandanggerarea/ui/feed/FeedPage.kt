package com.ex.scandanggerarea.ui.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ThumbDown
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.ex.scandanggerarea.data.model.ResponseFeeds
import com.ex.scandanggerarea.ui.AppScreen
import com.ex.scandanggerarea.utils.ResultState

@Composable
fun FeedPage(navController: NavHostController) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Feed")
        })
    }, floatingActionButton = {
        FloatingActionButton(onClick = {
            navController.navigate(AppScreen.CreateFeed.name)
        }) {
            Icon(
                Icons.Rounded.Add, contentDescription = "", tint = Color.White
            )
        }
    }) {
        FeedContent {
            navController.navigate(AppScreen.DetailPost.name)
        }
    }
}

@Preview(name = "List Feed")
@Composable
fun FeedContent(viewModel: FeedViewModel = hiltViewModel(), onClick: () -> Unit = {}) {
    when (val state = viewModel.state) {
        is ResultState.Success<*> -> {
            val response = state.data as ResponseFeeds
            LazyColumn(
                content = {
                    items(response.data.size) { test ->
                        CardItem(response.data[test], onClick)
                    }
                }, contentPadding = PaddingValues(
                    start = 16.dp, bottom = 80.dp, end = 16.dp, top = 16.dp
                )
            )
        }
        is ResultState.Error -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = state.e.message.toString(), color = Color.Red)
                Button(onClick = { viewModel.fetchFeeds() }) {
                    Text(text = "Refresh")
                }
            }
        }
        is ResultState.Loading -> {
            if (state.loading) Box(
                contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
        else -> {

        }
    }
}

@Preview(name = "Item Card", widthDp = 300)
@Composable
fun CardItem(data: ResponseFeeds.Data? = null, onClick: () -> Unit = {}) {
    Card(elevation = 5.dp, modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 16.dp)
        .clickable {
            onClick()
        }) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = data?.eventTitle ?: "",
                Modifier
                    .background(shape = RectangleShape, color = Color.White)
                    .padding(8.dp),
                fontWeight = FontWeight.Bold
            )
            Image(
                painter = rememberAsyncImagePainter(data?.imageUrl),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .background(shape = RectangleShape, color = Color.White)
                    .padding(8.dp)
                    .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(
                    modifier = Modifier
                        .background(shape = CircleShape, color = Color.Red)
                        .height(20.dp)
                        .width(20.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(text = "Kejahatan")
                Spacer(modifier = Modifier.weight(1f))
                Like("1000")
                Spacer(modifier = Modifier.width(8.dp))
                Dislike("1000")
            }
        }
    }
}

@Composable
fun Dislike(count: String) {
    Icon(imageVector = Icons.Rounded.ThumbDown, contentDescription = "")
    Text(text = count)
}

@Composable
fun Like(count: String) {
    Icon(imageVector = Icons.Rounded.ThumbUp, contentDescription = "")
    Text(text = count)
}