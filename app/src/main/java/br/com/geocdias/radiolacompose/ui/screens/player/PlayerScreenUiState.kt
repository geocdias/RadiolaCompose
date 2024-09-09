package br.com.geocdias.radiolacompose.ui.screens.player

import br.com.geocdias.radiolacompose.domain.model.Song

data class PlayerScreenUiState(
    val song: Song? = null,
    val totalDuration: Long = 0L,
    val currentDuration: Long = 0L,
    val progress: Float = 0F
)
