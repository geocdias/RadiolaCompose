package br.com.geocdias.radiolacompose.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.geocdias.radiolacompose.data.database.dao.SongDAO
import br.com.geocdias.radiolacompose.data.database.entities.SongEntity

@Database(
    version = 1,
    entities = [SongEntity::class]
)
abstract class RadiolaComposeDatabase : RoomDatabase() {
    abstract fun songDao(): SongDAO

    companion object {
        fun getInstance(context: Context): RadiolaComposeDatabase {
            return Room.databaseBuilder(
                context,
                RadiolaComposeDatabase::class.java,
                "radiola_compose.db"
            ).build()
        }
    }
}
