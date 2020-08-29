package com.cjuca.typecodealbums.core.repository

import com.cjuca.typecodealbums.core.model.Album
import com.cjuca.typecodealbums.core.model.User
import io.reactivex.Single

interface IAlbumsRepository {
    fun getUsers(): Single<List<User>>
    fun getAlbums(id: Long): Single<List<Album>>
}