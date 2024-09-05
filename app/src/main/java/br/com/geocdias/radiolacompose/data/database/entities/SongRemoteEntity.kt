package br.com.geocdias.radiolacompose.data.database.entities

data class SongRemoteEntity(
    val mediaId: String = "",
    val title: String = "",
    val artist: String = "",
    val songUrl: String = "",
    val imageUrl: String = ""
)

fun SongRemoteEntity.toSongEntity() = SongEntity(
    mediaId = mediaId,
    title = title,
    artist = artist,
    songUrl = songUrl,
    imageUrl = imageUrl

)
