package com.ex.scandanggerarea.ui.map

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ex.scandanggerarea.data.model.ResponseFeeds
import com.ex.scandanggerarea.ui.feed.Dislike
import com.ex.scandanggerarea.ui.feed.Like
import com.ex.scandanggerarea.utils.ResultState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapPage(
    navController: NavHostController, viewModel: MapViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Map") })
    }) {
        when (val state = viewModel.state) {
            is ResultState.Loading -> {
                if (state.loading) Box(
                    contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
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
            is ResultState.Success<*> -> {
                MapBody(state.data as ResponseFeeds)
            }
            else -> {

            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun FeatureThatRequiresLocationPermission() {
    val locationPermissionState = rememberPermissionState(
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )

    when (locationPermissionState.status) {
        PermissionStatus.Granted -> {
            Text("Camera permission Granted")
        }
        is PermissionStatus.Denied -> {
            Column {
                val textToShow =
                    if ((locationPermissionState.status as PermissionStatus.Denied).shouldShowRationale) {
                        "The camera is important for this app. Please grant the permission."
                    } else {
                        "Camera permission required for this feature to be available. " + "Please grant the permission"
                    }
                Text(textToShow)
                Button(onClick = { locationPermissionState.launchPermissionRequest() }) {
                    Text("Request permission")
                }
            }
        }
    }
}

@Composable
fun MapBody(responseFeeds: ResponseFeeds) {
    val latlongs = mutableListOf<LatLng>()
    responseFeeds.data.forEach {
        latlongs.add(LatLng(it.latitude.toDouble(), it.longitude.toDouble()))
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latlongs[0], 15f)
    }

    var clickZone by remember {
        mutableStateOf(false)
    }
    var posisi by remember {
        mutableStateOf(0)
    }

    val colorZone = listOf(
        Color.Red.copy(alpha = 0.1f),
        Color.Blue.copy(alpha = 0.1f),
        Color.Green.copy(alpha = 0.1f),
        Color.Yellow.copy(alpha = 0.1f)
    )

    val typeZone = listOf(
        "Kejahatan", "Bencana Alam", "Kecelakaan", "Other"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapClick = {
                clickZone = false
            }) {

            latlongs.forEachIndexed { index, latLng ->
                Circle(center = latLng,
                    fillColor = colorZone[index],
                    radius = responseFeeds.data[index].radius,
                    clickable = true,
                    onClick = {
                        posisi = index
                        clickZone = true
                    })
            }
        }

        AnimatedVisibility(
            visible = !clickZone,
            modifier = Modifier
                .align(Alignment.TopStart)
                .width(150.dp)
                .height(150.dp)
                .padding(8.dp)
        ) {
            Card {
                Column(
                    Modifier
                        .fillMaxSize()
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    CategoryDannger(color = Color.Red, des = "Kejahatan")
                    Spacer(modifier = Modifier.height(8.dp))
                    CategoryDannger(color = Color.Blue, des = "Bencana Alam")
                    Spacer(modifier = Modifier.height(8.dp))
                    CategoryDannger(color = Color.Green, des = "Kecelakaan")
                    Spacer(modifier = Modifier.height(8.dp))
                    CategoryDannger(color = Color.Yellow, des = "Other")
                }
            }
        }

        AnimatedVisibility(
            visible = clickZone, modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            ShowPopup(
                responseFeeds.data[posisi], colorZone[posisi], typeZone[posisi]
            ) {

            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true, name = "popup")
@Composable
fun ShowPopup(
    data: ResponseFeeds.Data? = null,
    color: Color = Color.Red,
    type: String = "",
    click: () -> Unit = {}
) {
    Card(elevation = 5.dp, onClick = click) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = data?.eventTitle ?: "")
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Spacer(
                    modifier = Modifier
                        .background(shape = CircleShape, color = color.copy(alpha = 1f))
                        .height(20.dp)
                        .width(20.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(text = type)
                Spacer(modifier = Modifier.weight(1f))
                Like("1000")
                Spacer(modifier = Modifier.width(8.dp))
                Dislike("1000")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryDannger(color: Color = Color.Red, des: String = "Kejahatan") {
    Row {
        Spacer(
            modifier = Modifier
                .background(shape = CircleShape, color = color)
                .height(20.dp)
                .width(20.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = des, fontSize = 10.sp
        )
    }
}