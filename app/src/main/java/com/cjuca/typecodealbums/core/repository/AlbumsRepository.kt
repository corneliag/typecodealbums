package com.cjuca.typecodealbums.core.repository

import com.cjuca.typecodealbums.core.model.User
import io.reactivex.Single

class AlbumsRepository(
    private val localRepository: ILocalRepository,
    private val remoteRepository: IRemoteRepository
) : IAlbumsRepository {

    override fun getUsers(): Single<List<User>> {
        return localRepository.getUsers()
            .filter { users -> users.isNotEmpty() }
            .switchIfEmpty(remoteRepository.getUsers())
    }
}