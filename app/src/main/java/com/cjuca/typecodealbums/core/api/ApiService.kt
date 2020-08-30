package com.cjuca.typecodealbums.core.api

import com.cjuca.typecodealbums.core.model.Album
import com.cjuca.typecodealbums.core.model.Photo
import com.cjuca.typecodealbums.core.model.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        private const val USER_ID = "userId"
        private const val ALBUM_ID = "albumId"
    }

    @GET("/users")
    fun getUsers(): Single<List<User>>

    @GET("/albums")
    fun getAlbumsByUser(@Query(USER_ID) userId: Long): Single<List<Album>>

    @GET("/photos")
    fun getPhotosByAlbum(@Query(ALBUM_ID) albumId: Long): Single<List<Photo>>
}