package com.cjuca.typecodealbums.core.api

import com.cjuca.typecodealbums.core.model.Album
import com.cjuca.typecodealbums.core.model.Photo
import com.cjuca.typecodealbums.core.model.User
import io.reactivex.Single

interface IApiClient {
    fun initializeClient()
    fun getUsers(): Single<List<User>>
    fun getAlbumsByUser(userId: Long): Single<List<Album>>
    fun getPhotosByAlbum(albumId: Long): Single<List<Photo>>
}