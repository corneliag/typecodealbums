package com.cjuca.typecodealbums.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cjuca.typecodealbums.core.dao.AlbumDao
import com.cjuca.typecodealbums.core.dao.PhotoDao
import com.cjuca.typecodealbums.core.dao.UserDao
import com.cjuca.typecodealbums.core.model.Album
import com.cjuca.typecodealbums.core.model.Photo
import com.cjuca.typecodealbums.core.model.User

@Database(
    entities = [User::class, Album::class, Photo::class],
    version = 1,
    exportSchema = false
)
abstract class AlbumsRoomDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
    abstract fun userDao(): UserDao
    abstract fun photoDao(): PhotoDao

    companion object {
        @Volatile
        private var INSTANCE: AlbumsRoomDatabase? = null

        fun getDatabase(context: Context): AlbumsRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AlbumsRoomDatabase::class.java,
                    "album_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
