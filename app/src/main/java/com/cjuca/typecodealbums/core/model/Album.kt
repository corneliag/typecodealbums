package com.cjuca.typecodealbums.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album_table")
data class Album(
    @PrimaryKey val id: Long,
    val userId: Long,
    val title: String
)
