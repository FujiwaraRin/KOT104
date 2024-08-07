package com.fpoly.huyndph30375_assignment_kot104.bottom_nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fpoly.huyndph30375_assignment_kot104.ui.screens.CartScreen
import com.fpoly.huyndph30375_assignment_kot104.ui.screens.CheckOutScreen
import com.fpoly.huyndph30375_assignment_kot104.ui.screens.FavoriteScreen
import com.fpoly.huyndph30375_assignment_kot104.ui.screens.HomeScreen
import com.fpoly.huyndph30375_assignment_kot104.ui.screens.ItemProduct
import com.fpoly.huyndph30375_assignment_kot104.ui.screens.NotificationScreen
import com.fpoly.huyndph30375_assignment_kot104.ui.screens.SettingsScreen
import com.fpoly.huyndph30375_assignment_kot104.ui.screens.SignInScreen
import com.fpoly.huyndph30375_assignment_kot104.ui.screens.SignUpScreen
import com.fpoly.huyndph30375_assignment_kot104.ui.screens.WelcomeScreen

@Composable
fun ScreenNav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome") {
        composable(Screens.welcome) {
            WelcomeScreen(navController = navController)
        }
        composable(Screens.bottomApp) {
            BottomApp(navControllerMain = navController)
        }
        composable(Screens.signin) {
            SignInScreen(navController = navController)
        }
        composable(Screens.signUp) {
            SignUpScreen(navController = navController)
        }
        composable("${Screens.itemProduct}/{id}") { backStack ->
            val id = backStack.arguments?.getString("id")
            ItemProduct(
                navController = navController,
                id = id ?: ""
            )
        }
        composable(Screens.cart) {
            CartScreen(navController = navController)
        }
        composable("${Screens.checkOut}/{total}") { backStack ->
            val total = backStack.arguments?.getString("total")
            CheckOutScreen(navController = navController, total = total ?: "")
        }
        composable(Screens.notification) {
            NotificationScreen(navController = navController)
        }
        composable(Screens.home) {
            HomeScreen(navController = navController)
        }
        composable(Screens.favorite) {
            FavoriteScreen(navController = navController)
        }
        composable(Screens.setting){
            SettingsScreen(navController = navController)
        }
    }
}