package com.ex.scandanggerarea.ui.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ex.scandanggerarea.data.local.SharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val sharedPref: SharedPref
) : ViewModel() {
    val isLogin by mutableStateOf(sharedPref.isLogin)

    fun setLogin(isLogin: Boolean) {
        sharedPref.isLogin = isLogin
    }
}