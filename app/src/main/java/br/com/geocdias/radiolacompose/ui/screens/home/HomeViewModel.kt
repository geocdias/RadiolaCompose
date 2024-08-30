package br.com.geocdias.radiolacompose.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.geocdias.radiolacompose.data.repositories.SongsRepository
import br.com.geocdias.radiolacompose.model.Song
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: SongsRepository) : ViewModel() {

    private val _songsFlow = MutableStateFlow<List<Song>>(emptyList())
    val songsFlow = repository.getAllSongs()

    fun getAllSongs() {
        viewModelScope.launch {
           // if (songsFlow.last().isNullOrEmpty()) {
                val songs = repository.fetchAllSongsFromRemote()
                println(songs)
           // }
        }
    }
}
