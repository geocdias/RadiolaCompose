package br.com.geocdias.radiolacompose.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.geocdias.radiolacompose.ui.screens.home.HomeScreen

internal const val homeRoute = "home"

fun NavGraphBuilder.homeScreen(navController: NavHostController) {
    composable(route = homeRoute) {
        HomeScreen(onClick = {
            navController.navigateToPlayer(it)
        })
    }
}

fun NavController.navigateToHome() {
    navigate(homeRoute)
}
