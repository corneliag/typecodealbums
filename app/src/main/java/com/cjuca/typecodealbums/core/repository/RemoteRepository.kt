package com.cjuca.typecodealbums.core.repository

import com.cjuca.typecodealbums.core.ApiService
import com.cjuca.typecodealbums.core.model.Album
import com.cjuca.typecodealbums.core.model.User
import io.reactivex.Single

class RemoteRepository(private val apiClient: ApiService) : IRemoteRepository {

    override fun getUsers(): Single<List<User>> {
        return apiClient.getUsers()
    }

    override fun getAlbums(id: Long): Single<List<Album>> {
        return apiClient.getAlbumsByUser(id)
    }
}