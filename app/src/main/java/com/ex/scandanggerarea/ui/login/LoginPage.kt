package com.ex.scandanggerarea.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ex.scandanggerarea.ui.AppScreen

@Composable
fun LoginPage(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState) {
        Box {
            BodyLogin({
                viewModel.login(navController)
            }, {
                navController.navigate(AppScreen.Signup.name)
            })
            if (viewModel.loadingState) CircularProgressIndicator(
                modifier = Modifier.align(
                    Alignment.Center
                )
            )
            Text(
                text = viewModel.errorState,
                color = Color.Red,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}

@Preview(showBackground = true, name = "screen")
@Composable
fun SigninPreview() {
    BodyLogin({

    }, {

    })
}

@Composable
fun BodyLogin(
    clickLogin: () -> Unit, clickRegister: () -> Unit, viewModel: LoginViewModel = hiltViewModel()
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Title("Login")
        OutlinedTextField(value = viewModel.email,
            singleLine = true,
            onValueChange = { viewModel.email = it },
            label = { Text("Email") })
        OutlinedTextField(value = viewModel.pwd,
            singleLine = true,
            onValueChange = { viewModel.pwd = it },
            label = { Text("Password") })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = {
                clickLogin()
            }) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {
                clickRegister()
            }) {
                Text(text = "Register")
            }
        }
    }
}

@Preview(name = "Title View", showBackground = true)
@Composable
fun Title(text: String = "Title") {
    Text(text = text, fontSize = 35.sp, fontWeight = FontWeight.Black)
}