package com.cjuca.typecodealbums.core.repository

import com.cjuca.typecodealbums.core.model.Album
import com.cjuca.typecodealbums.core.model.Photo
import com.cjuca.typecodealbums.core.model.User
import io.reactivex.Completable
import io.reactivex.Single
import kotlinx.coroutines.Job

interface ILocalRepository {
    fun getUsers(): Single<List<User>>
    fun getAlbums(id: Long): Single<List<Album>>
    fun getPhotos(id: Long): Single<List<Photo>>
    fun addUsers(userList: List<User>): Job
    fun addAlbums(albumList: List<Album>): Job
    fun addPhotos(photoList: List<Photo>): Job
    fun clearTable(): Completable
}
