package com.swancodes.travelapp.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.swancodes.travelapp.presentation.ui.screen.TripDetailScreen
import com.swancodes.travelapp.presentation.ui.screen.CityScreen
import com.swancodes.travelapp.presentation.ui.screen.DateScreen
import com.swancodes.travelapp.presentation.ui.screen.PlanTripScreen
import com.swancodes.travelapp.presentation.viewmodel.TravelViewModel

sealed class AppScreen(val route: String) {
    object PlanTrip : AppScreen("planTrip")
    object City : AppScreen("city")
    object Date : AppScreen("date")
    object Detail : AppScreen("detail")

}

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val viewModel: TravelViewModel = viewModel()

    NavHost(navController = navController, startDestination = AppScreen.PlanTrip.route) {
        composable(AppScreen.PlanTrip.route) {
            PlanTripScreen(navController, viewModel)
        }

        composable(AppScreen.City.route) {
            CityScreen(
                navController, viewModel = viewModel
            )
        }

        composable(AppScreen.Date.route) {
            DateScreen(navController, viewModel = viewModel
            )
        }

        composable(AppScreen.Detail.route) {
            TripDetailScreen(navController, viewModel = viewModel
            )
        }
    }
}