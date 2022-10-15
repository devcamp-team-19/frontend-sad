package com.ex.scandanggerarea.ui.signup

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ex.scandanggerarea.ui.AppScreen
import com.ex.scandanggerarea.ui.login.Title

@Composable
fun SignupPage(navHostController: NavHostController) {
    Scaffold {
        Body {
            navHostController.navigate(AppScreen.Home.name)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignupPreview() {
    Body {

    }
}

@Composable
fun Body(viewModel: SignupViewModel = hiltViewModel(), onClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp)
    ) {
        Title(text = "Sign Up")
        Spacer(Modifier.height(20.dp))
        OutlinedTextField(
            singleLine = true,
            value = "",
            onValueChange = { },
            label = { Text("Nama Lengkap") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            singleLine = true,
            value = "",
            onValueChange = { },
            label = { Text("NIK") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            singleLine = true,
            value = "",
            onValueChange = { },
            label = { Text("Address") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            singleLine = true,
            value = "",
            onValueChange = { },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            singleLine = true,
            value = "",
            onValueChange = { },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onClick, modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Sign Up")
        }
    }
}