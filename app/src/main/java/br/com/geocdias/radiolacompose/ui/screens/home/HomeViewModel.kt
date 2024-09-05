package br.com.geocdias.radiolacompose.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.geocdias.radiolacompose.domain.repository.SongsRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: SongsRepository) : ViewModel() {

    val songsFlow = repository.getAllSongs().map { songs ->
        HomeScreenUiState(songs)
    }

    fun getAllSongs() {
        viewModelScope.launch {
            val songs = repository.fetchAllSongsFromRemote()
            println(songs)

        }
    }
}
