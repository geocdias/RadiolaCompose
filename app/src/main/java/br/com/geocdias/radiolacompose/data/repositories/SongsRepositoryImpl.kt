package br.com.geocdias.radiolacompose.data.repositories

import br.com.geocdias.radiolacompose.data.database.entities.SongRemoteEntity
import br.com.geocdias.radiolacompose.data.database.entities.toSong
import br.com.geocdias.radiolacompose.data.database.entities.toSongEntity
import br.com.geocdias.radiolacompose.data.datasources.LocalSongsDatasource
import br.com.geocdias.radiolacompose.data.datasources.RemoteSongsDatasource
import br.com.geocdias.radiolacompose.domain.model.Song
import br.com.geocdias.radiolacompose.domain.repository.SongsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SongsRepositoryImpl(
    private val remoteDatasource: RemoteSongsDatasource,
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

    override suspend fun fetchSongById(mediaId: String): Song? {
        return localDatasource.getSongById(mediaId)?.toSong()
    }
}
