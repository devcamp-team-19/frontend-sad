package com.ex.scandanggerarea.ui.createfeed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun CreateFeedPage(navHostController: NavHostController) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Create Feed") }, navigationIcon = {
            IconButton(onClick = {
                navHostController.popBackStack()
            }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "back")
            }
        })
    }) {
        DetailBody {
            navHostController.popBackStack()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailBody(click: () -> Unit = {}) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        item {
            Image(
                Icons.Filled.Image,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                singleLine = true,
                value = "",
                onValueChange = { },
                label = { Text("Name incident") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                singleLine = true,
                value = "",
                onValueChange = { },
                label = { Text("Category") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                singleLine = true,
                value = "",
                onValueChange = { },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {

                }, modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Upload Incident")
            }
        }
    }
}