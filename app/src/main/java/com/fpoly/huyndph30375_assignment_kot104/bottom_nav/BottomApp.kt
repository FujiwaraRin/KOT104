package com.fpoly.huyndph30375_assignment_kot104.bottom_nav

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fpoly.huyndph30375_assignment_kot104.model.IconItems
import com.fpoly.huyndph30375_assignment_kot104.ui.screens.FavoriteScreen
import com.fpoly.huyndph30375_assignment_kot104.ui.screens.HomeScreen
import com.fpoly.huyndph30375_assignment_kot104.ui.screens.NotificationScreen
import com.fpoly.huyndph30375_assignment_kot104.ui.screens.SettingsScreen

@Composable
fun BottomApp(navControllerMain: NavController) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.home,
            Modifier.padding(innerPadding)
        ) {
            composable(Screens.home) {
                HomeScreen(navController = navControllerMain)
            }
            composable(Screens.favorite) {
                FavoriteScreen(navController = navControllerMain)
            }
            composable(Screens.notificationBottom) {
                NotificationScreen(navController = navControllerMain)
            }
            composable(Screens.person) {
                SettingsScreen(navController = navControllerMain)
            }

        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val items = listOf(
        IconItems(Icons.Filled.Home, Icons.Outlined.Home, Screens.home),
        IconItems(Icons.Filled.Favorite, Icons.Outlined.FavoriteBorder, Screens.favorite),
        IconItems(
            Icons.Filled.Notifications,
            Icons.Outlined.Notifications,
            Screens.notificationBottom
        ),
        IconItems(Icons.Filled.Person, Icons.Outlined.Person, Screens.person)
    )
    var selectedItem by remember {
        mutableIntStateOf(0)
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation(backgroundColor = Color.White) {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        painter = when {
                            selectedItem == index -> rememberVectorPainter(image = item.selectedIcon)
                            else -> rememberVectorPainter(image = item.unselectedIcon)
                        },
                        contentDescription = null,
                    )
                })
        }
    }
}