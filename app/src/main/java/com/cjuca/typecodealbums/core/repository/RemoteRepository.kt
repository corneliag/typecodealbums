package com.cjuca.typecodealbums.core.repository

import com.cjuca.typecodealbums.core.api.ApiClient
import com.cjuca.typecodealbums.core.model.Album
import com.cjuca.typecodealbums.core.model.Photo
import com.cjuca.typecodealbums.core.model.User
import io.reactivex.Single

class RemoteRepository(private val apiClient: ApiClient) : IRemoteRepository {

    override fun getUsers(): Single<List<User>> {
        return apiClient.getUsers()
    }

    override fun getAlbums(id: Long): Single<List<Album>> {
        return apiClient.getAlbumsByUser(id)
    }

    override fun getPhotos(id: Long): Single<List<Photo>> {
        return apiClient.getPhotosByAlbum(id)
    }
}