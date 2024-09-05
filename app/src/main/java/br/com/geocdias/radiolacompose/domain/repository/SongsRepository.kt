package br.com.geocdias.radiolacompose.domain.repository

import br.com.geocdias.radiolacompose.domain.model.Song
import kotlinx.coroutines.flow.Flow

interface SongsRepository {
    fun getAllSongs(): Flow<List<Song>>
    suspend fun fetchAllSongsFromRemote()
    suspend fun fetchSongById(mediaId: String): Song?
}
