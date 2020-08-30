package com.cjuca.typecodealbums.core.api

import android.content.Context
import com.cjuca.typecodealbums.R
import com.cjuca.typecodealbums.core.model.Album
import com.cjuca.typecodealbums.core.model.Photo
import com.cjuca.typecodealbums.core.model.User
import com.facebook.stetho.okhttp3.StethoInterceptor
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient(private val context: Context) : IApiClient {

    private lateinit var service: ApiService
    override fun initializeClient() {
        OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor()).build()
        create(context)
    }

    private fun create(context: Context) {
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(context.getString(R.string.configurationUrl))
            .build()

        service = retrofit.create(ApiService::class.java)
    }

    override fun getUsers(): Single<List<User>> = service.getUsers()

    override fun getAlbumsByUser(userId: Long): Single<List<Album>> =
        service.getAlbumsByUser(userId)

    override fun getPhotosByAlbum(albumId: Long): Single<List<Photo>> =
        service.getPhotosByAlbum(albumId)
}