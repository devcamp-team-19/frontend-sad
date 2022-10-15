package com.ex.scandanggerarea.ui.home

import androidx.annotation.StringRes
import com.ex.scandanggerarea.R
import com.ex.scandanggerarea.ui.AppScreen

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Profile : Screen(AppScreen.Profile.name, R.string.profile)
    object Map : Screen(AppScreen.Map.name, R.string.map)
    object Feed : Screen(AppScreen.Feed.name, R.string.feed)
}