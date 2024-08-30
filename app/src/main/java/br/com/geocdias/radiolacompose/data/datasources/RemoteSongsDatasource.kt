package br.com.geocdias.radiolacompose.data.datasources

import br.com.geocdias.radiolacompose.data.database.entities.SongEntity
import br.com.geocdias.radiolacompose.data.database.entities.SongRemoteEntity
import br.com.geocdias.radiolacompose.model.Song
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

interface RemoteSongsDatasource {
    suspend fun getAllSongs(): List<SongRemoteEntity>
}

class RemoteSongsDatasourceImpl : RemoteSongsDatasource {
    private val firestore = FirebaseFirestore.getInstance()
    private val singleCollection = firestore.collection("songs")

    override suspend fun getAllSongs(): List<SongRemoteEntity> {
        return withContext(Dispatchers.IO) {
            try {
                singleCollection.get().await().toObjects(SongRemoteEntity::class.java)
            } catch (e: Exception) {
                println("ERROR ${e}")
                emptyList()
            }
        }
    }
}
