package br.com.geocdias.radiolacompose.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.geocdias.radiolacompose.model.Song

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey(autoGenerate = true,)
    val id: Long,
    val mediaId: String,
    val title: String,
    val artist: String,
    val songUrl: String,
    val imageUrl: String
)

fun SongEntity.toSong() = Song(
    mediaId = mediaId,
    title = title,
    artist = artist,
    songUrl = songUrl,
    imageUrl = imageUrl
)
