package com.ex.scandanggerarea.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.ex.scandanggerarea.ui.AppScreen

@Composable
fun ProfilePage(navController: NavHostController) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Profile") })
    }) {
        ProfilContent({
            navController.popBackStack(AppScreen.Login.name, inclusive = false)
        })
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilContent(click: () -> Unit = {}, viewModel: ProfileViewModel = hiltViewModel()) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .fillMaxHeight()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberAsyncImagePainter("https://s3.theasianparent.com/cdn-cgi/image/height=250/tap-assets-prod/wp-content/uploads/sites/24/2018/12/bayi-menjulurkan-lidahnya-lead.jpg"),
                contentDescription = "",
                Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .padding(16.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Plosaa", fontSize = 25.sp)
                Text(text = "Constribute: 40000 Feeds")
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Leaderboard, contentDescription = "")
                    Spacer(Modifier.width(8.dp))
                    Text(text = "Grand Master")
                }
            }
        }
        Button(
            onClick = {
                viewModel.setLogin(false)
                click()
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Logout")
        }
    }
}