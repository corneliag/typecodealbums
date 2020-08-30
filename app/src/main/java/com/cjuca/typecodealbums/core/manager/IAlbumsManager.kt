package com.cjuca.typecodealbums.core.manager

import com.cjuca.typecodealbums.core.model.Album
import com.cjuca.typecodealbums.core.model.Photo
import com.cjuca.typecodealbums.core.model.User
import io.reactivex.Single

interface IAlbumsManager {

    fun getUsers(): Single<List<User>>
    fun getAlbums(id: Long): Single<List<Album>>
    fun getPhotos(id: Long): Single<List<Photo>>
}