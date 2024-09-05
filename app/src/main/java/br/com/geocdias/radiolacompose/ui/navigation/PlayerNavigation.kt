package br.com.geocdias.radiolacompose.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.geocdias.radiolacompose.ui.screens.player.PlayerScreen

internal const val playerRoute = "player"
internal const val songId = "songId"

fun NavGraphBuilder.playerScreen(

) {
    composable(route = "$playerRoute/{$songId}") {
        val songId = it.arguments?.getString(songId).orEmpty()
        PlayerScreen(
            songId = songId
        )
    }
}

fun NavController.navigateToPlayer(songId: String) {
    navigate("$playerRoute/$songId")
}
