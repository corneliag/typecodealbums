package com.cjuca.typecodealbums.core.manager

import com.cjuca.typecodealbums.core.model.User
import com.cjuca.typecodealbums.core.repository.IAlbumsRepository
import io.reactivex.Single

class AlbumsManager(private val repository: IAlbumsRepository) : IAlbumsManager {

    override fun getUsers(): Single<List<User>> {
        return repository.getUsers()
    }
}