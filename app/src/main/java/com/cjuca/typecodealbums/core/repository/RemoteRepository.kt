package com.cjuca.typecodealbums.core.repository

import com.cjuca.typecodealbums.core.ApiService
import com.cjuca.typecodealbums.core.model.User
import io.reactivex.Single

class RemoteRepository(private val apiClient: ApiService) : IRemoteRepository {

    override fun getUsers(): Single<List<User>> {
        return apiClient.getUsers()
    }
}