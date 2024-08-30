package br.com.geocdias.radiolacompose.data.repositories

import br.com.geocdias.radiolacompose.data.datasources.RemoteSongsDatasource
import br.com.geocdias.radiolacompose.data.database.entities.SongEntity
import br.com.geocdias.radiolacompose.data.database.entities.SongRemoteEntity
import br.com.geocdias.radiolacompose.data.database.entities.toSong
import br.com.geocdias.radiolacompose.data.database.entities.toSongEntity
import br.com.geocdias.radiolacompose.data.datasources.LocalSongsDatasource
import br.com.geocdias.radiolacompose.model.Song
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface SongsRepository {
    fun getAllSongs(): Flow<List<Song>>
    suspend fun fetchAllSongsFromRemote()
}

class SongsRepositoryImpl(
    private val remoteDatasource :RemoteSongsDatasource,
    private val localDatasource: LocalSongsDatasource
) : SongsRepository {
    override fun getAllSongs(): Flow<List<Song>> {
        return localDatasource.getAllSongs().map { songsEntity ->
            songsEntity.map { it.toSong() }
        }
    }

    override suspend fun fetchAllSongsFromRemote() {
        val songs = remoteDatasource.getAllSongs().map(SongRemoteEntity::toSongEntity)
        localDatasource.saveAllSongs(songs)
    }
}
