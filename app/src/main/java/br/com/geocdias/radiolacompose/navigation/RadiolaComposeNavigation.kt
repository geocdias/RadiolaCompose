package br.com.geocdias.radiolacompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun RadiolaComposeNavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        startDestination = homeRoute,
        modifier = modifier,
        navController = navController
    ) {
        homeScreen(navController)
        playerScreen()

    }
}
