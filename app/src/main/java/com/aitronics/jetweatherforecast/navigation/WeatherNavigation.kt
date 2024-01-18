package com.aitronics.jetweatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aitronics.jetweatherforecast.screens.main.MainViewModel
import com.aitronics.jetweatherforecast.screens.main.WeatherMainScreen
import com.aitronics.jetweatherforecast.screens.search.WeatherSearch
import com.aitronics.jetweatherforecast.screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = WeatherScreens.SplashScreen.name) {

        composable(WeatherScreens.SplashScreen.name) {
            WeatherSplashScreen(navController = navController)
        }

        val route = WeatherScreens.MainScreen.name

        composable("$route/{city}",
            arguments = listOf(
                navArgument(name = "city") {
                    type = NavType.StringType
                }
            )
        ) { navBundle ->
            navBundle.arguments?.getString("city").let {
                val mainViewModel = hiltViewModel<MainViewModel>()
                WeatherMainScreen(navController = navController,it,mainViewModel)
            }
        }

        composable(WeatherScreens.SearchScreen.name) {
            val mainViewModel = hiltViewModel<MainViewModel>()
            WeatherSearch(navController = navController,mainViewModel)
        }

    }
}