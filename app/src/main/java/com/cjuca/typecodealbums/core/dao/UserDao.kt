package com.cjuca.typecodealbums.core.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cjuca.typecodealbums.core.model.User

@Dao
interface UserDao {

    @Query("SELECT * from user_table")
    fun getUsers(): List<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()
}