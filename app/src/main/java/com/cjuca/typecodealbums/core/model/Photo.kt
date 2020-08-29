package com.cjuca.typecodealbums.core.model

data class Photo(
    val id: Int,
    val albumId: Long,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)
