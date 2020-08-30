package com.cjuca.typecodealbums.core.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cjuca.typecodealbums.core.model.Photo

@Dao
interface PhotoDao {

    @Query("SELECT * from photo_table where albumId = :albumId")
    fun getPhotos(albumId: Long): List<Photo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(photo: Photo)

    @Query("DELETE FROM photo_table")
    suspend fun deleteAll()
}