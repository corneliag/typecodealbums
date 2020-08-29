package com.cjuca.typecodealbums.core.repository

import com.cjuca.typecodealbums.core.model.Album
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

    override fun getAlbums(id: Long): Single<List<Album>> {
        return localRepository.getAlbums(id)
            .filter { albums -> albums.isNotEmpty() }
            .switchIfEmpty(remoteRepository.getAlbums(id))
    }
}