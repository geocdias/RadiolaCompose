package br.com.geocdias.radiolacompose.ui.screens.home

import br.com.geocdias.radiolacompose.domain.model.Song

data class HomeScreenUiState(
    val songs: List<Song> = emptyList()
)
