package br.com.geocdias.radiolacompose.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import br.com.geocdias.radiolacompose.data.database.entities.SongEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSong(song: SongEntity)

    @Transaction
    suspend fun saveAllSongs(song: List<SongEntity>) {
        song.forEach {
            saveSong(it)
        }
    }

    @Query("SELECT * FROM songs")
    fun getAllSongs(): Flow<List<SongEntity>>

    @Query("SELECT * FROM songs WHERE mediaId = :mediaId")
    suspend fun getSongById(mediaId: String): SongEntity?
}
