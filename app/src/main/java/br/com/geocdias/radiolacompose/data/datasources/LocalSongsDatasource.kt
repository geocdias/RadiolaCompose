package br.com.geocdias.radiolacompose.data.datasources

import br.com.geocdias.radiolacompose.data.database.RadiolaComposeDatabase
import br.com.geocdias.radiolacompose.data.database.dao.SongDAO
import br.com.geocdias.radiolacompose.data.database.entities.SongEntity
import kotlinx.coroutines.flow.Flow

interface LocalSongsDatasource {
    suspend fun saveSong(song: SongEntity)
    suspend fun saveAllSongs(songs: List<SongEntity>?)
    fun getAllSongs(): Flow<List<SongEntity>>
    suspend fun getSongById(mediaId: String): SongEntity?
}

class LocalSongsDatasourceImpl(private val songDao: SongDAO) : LocalSongsDatasource {

    override suspend fun saveSong(song: SongEntity) {
        songDao.saveSong(song)
    }

    override suspend fun saveAllSongs(songs: List<SongEntity>?) {
        if (songs.isNullOrEmpty()) return
        songDao.saveAllSongs(songs)
    }

    override fun getAllSongs(): Flow<List<SongEntity>> {
        return songDao.getAllSongs()
    }

    override suspend fun getSongById(mediaId: String): SongEntity? {
        return songDao.getSongById(mediaId)
    }


}
