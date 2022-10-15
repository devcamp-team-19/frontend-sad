package com.ex.scandanggerarea.ui.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun MapPage(navController: NavHostController) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Map") })
    }) {
        MapBody()
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun FeatureThatRequiresCameraPermission() {
    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )

    when (cameraPermissionState.status) {
        PermissionStatus.Granted -> {
            Text("Camera permission Granted")
        }
        is PermissionStatus.Denied -> {
            Column {
                val textToShow =
                    if ((cameraPermissionState.status as PermissionStatus.Denied).shouldShowRationale) {
                        "The camera is important for this app. Please grant the permission."
                    } else {
                        "Camera permission required for this feature to be available. " + "Please grant the permission"
                    }
                Text(textToShow)
                Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
                    Text("Request permission")
                }
            }
        }
    }
}

@Preview
@Composable
fun MapBody() {
    val singapore = LatLng(1.35, 103.87)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(), cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = singapore),
                title = "Singapore",
                snippet = "Marker in Singapore"
            )
            Circle(center = singapore)
        }
        Card(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .width(150.dp)
                .height(150.dp)
                .padding(8.dp)
        ) {
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