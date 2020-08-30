package com.cjuca.typecodealbums.core.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cjuca.typecodealbums.core.model.Album

@Dao
interface AlbumDao {

    @Query("SELECT * from album_table where userId = :userId")
    fun getAlbums(userId: Long): List<Album>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(album: Album)

    @Query("DELETE FROM album_table")
    suspend fun deleteAll()
}