package com.ex.scandanggerarea.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.ex.scandanggerarea.data.Repository
import com.ex.scandanggerarea.data.model.BodyLogin
import com.ex.scandanggerarea.ui.AppScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    var email by mutableStateOf("")
    var pwd by mutableStateOf("")
    var loadingState by mutableStateOf(false)
    var errorState by mutableStateOf("")
    var successState by mutableStateOf(false)
    var isLogin by mutableStateOf(repo.isLogin())

    fun login(navController: NavHostController) {
        val body = BodyLogin(email, pwd)
        repo.login(body).doOnSubscribe {
            loadingState = true
        }.subscribe({
            loadingState = false
            successState = true
            navController.navigate(AppScreen.Home.name)
        }, {
            loadingState = false
            errorState = it.message.toString()
        })
    }
}