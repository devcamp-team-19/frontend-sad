package com.ex.scandanggerarea.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ex.scandanggerarea.data.local.SharedPref
import com.ex.scandanggerarea.ui.createfeed.CreateFeedPage
import com.ex.scandanggerarea.ui.detail.DetailFeedPage
import com.ex.scandanggerarea.ui.home.HomePage
import com.ex.scandanggerarea.ui.login.LoginPage
import com.ex.scandanggerarea.ui.signup.SignupPage
import com.ex.scandanggerarea.ui.theme.ScanDanggerAreaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScanDanggerAreaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    SadApp()
                }
            }
        }
    }
}

enum class AppScreen {
    Login, Signup, Map, Feed, Profile, CreateFeed, DetailPost, Home
}

@Composable
fun SadApp() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val shared = SharedPref(context)
    NavHost(
        navController = navController,
        startDestination = if (shared.isLogin) AppScreen.Home.name else AppScreen.Login.name
    ) {
        composable(route = AppScreen.Login.name) {
            LoginPage(navController)
        }
        composable(AppScreen.Signup.name) {
            SignupPage(navController)
        }
        composable(AppScreen.Home.name) {
            HomePage(navController)
        }
        composable(
            AppScreen.DetailPost.name.plus("/{test}"),
            arguments = listOf(navArgument("test") { type = NavType.StringType })
        ) {
            DetailFeedPage(navController, it.arguments?.getString("test"))
        }
        composable(AppScreen.CreateFeed.name) {
            CreateFeedPage(navController)
        }
    }
}
