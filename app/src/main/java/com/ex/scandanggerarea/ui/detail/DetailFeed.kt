package com.ex.scandanggerarea.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ex.scandanggerarea.R
import com.ex.scandanggerarea.ui.feed.Dislike
import com.ex.scandanggerarea.ui.feed.Like

@Composable
fun DetailFeedPage(navController: NavHostController) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Detail Incident") }, navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "back")
            }
        })
    }) {
        DetailBody()
    }
}

@Preview(showBackground = true)
@Composable
fun DetailBody() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
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

            Text(
                text = "Tawuran Gengster",
                Modifier
                    .background(shape = RectangleShape, color = Color.White)
                    .fillMaxWidth()
                    .padding(16.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit ut aliquam, purus sit amet luctus venenatis, lectus magna fringilla urna, porttitor rhoncus dolor purus non enim praesent elementum facilisis leo, vel",
                Modifier
                    .background(shape = RectangleShape, color = Color.White)
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Justify
            )
            Text(
                text = "Comment (30)",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp),
                fontSize = 16.sp
            )
            Text(
                text = "Sukaji", fontWeight = FontWeight.Medium, modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                ), fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Wah iya kemarin aku lewat situ rame banget, banyak yang korbannya",
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                )
            )
            Text(
                text = "Reply",
                color = Color.Gray,
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp
                ).fillMaxWidth(),
                textAlign = TextAlign.End
            )
        }
    }
}