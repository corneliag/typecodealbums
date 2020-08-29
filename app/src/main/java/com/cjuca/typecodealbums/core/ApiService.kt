package com.cjuca.typecodealbums.core

import android.content.Context
import com.cjuca.typecodealbums.R
import com.cjuca.typecodealbums.core.model.Album
import com.cjuca.typecodealbums.core.model.Photo
import com.cjuca.typecodealbums.core.model.User
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        private const val USER_ID = "userId"
        private const val ALBUM_ID = "albumId"

        fun create(context: Context): ApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(context.getString(R.string.configurationUrl))
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }

    @GET("/users")
    fun getUsers(): Single<List<User>>

    @GET("/albums")
    fun getAlbumsByUser(@Query(USER_ID) userId: Long): Single<List<Album>>

    @GET("/photos")
    fun getPhotosByAlbum(@Query(ALBUM_ID) albumId: Long): Single<List<Photo>>
}