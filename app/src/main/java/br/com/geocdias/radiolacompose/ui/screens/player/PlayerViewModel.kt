package br.com.geocdias.radiolacompose.ui.screens.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.geocdias.radiolacompose.domain.repository.SongsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlayerViewModel(private val repository: SongsRepository) : ViewModel() {
    private val _songFlow  = MutableStateFlow(PlayerScreenUiState())
    val songFlow = _songFlow.asStateFlow()

    fun getSongById(mediaId: String) {
        viewModelScope.launch {
            println("getSongById $mediaId")
            val song = repository.fetchSongById(mediaId)
            println("song $song")
            song?.let {
                _songFlow.update { currentState ->
                    currentState.copy( song = it)
                }
            }
        }
    }
}
