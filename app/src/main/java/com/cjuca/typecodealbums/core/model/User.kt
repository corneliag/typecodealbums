package com.cjuca.typecodealbums.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey val id: Long,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String
)
