package com.ex.scandanggerarea.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Feed
import androidx.compose.material.icons.rounded.ManageAccounts
import androidx.compose.material.icons.rounded.Map
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ex.scandanggerarea.ui.feed.FeedPage
import com.ex.scandanggerarea.ui.map.MapPage
import com.ex.scandanggerarea.ui.profile.ProfilePage

@Composable
fun HomePage(parentNavController: NavHostController) {
    val items = listOf(
        Screen.Map,
        Screen.Feed,
        Screen.Profile,
    )

    val iconsItem = listOf(
        Icons.Rounded.Map,
        Icons.Rounded.Feed,
        Icons.Rounded.ManageAccounts,
    )

    val navController = rememberNavController()
    Scaffold(bottomBar = {
        BottomNavigation {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            items.forEachIndexed { index, screen ->
                BottomNavigationItem(icon = {
                    Icon(
                        iconsItem[index], contentDescription = null
                    )
                },
                    label = { Text(stringResource(screen.resourceId)) },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    })
            }
        }
    }) { innerPadding ->
        NavHost(
            navController, startDestination = Screen.Map.route, Modifier.padding(innerPadding)
        ) {
            composable(Screen.Map.route) { MapPage(navController) }
            composable(Screen.Feed.route) { FeedPage(parentNavController) }
            composable(Screen.Profile.route) { ProfilePage(parentNavController) }
        }
    }
}