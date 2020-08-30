package com.cjuca.typecodealbums.core.repository

import com.cjuca.typecodealbums.core.model.Album
import com.cjuca.typecodealbums.core.model.Photo
import com.cjuca.typecodealbums.core.model.User
import io.reactivex.Single

class AlbumsRepository(
    private val localRepository: ILocalRepository,
    private val remoteRepository: IRemoteRepository
) : IAlbumsRepository {

    override fun getUsers(): Single<List<User>> {
        return localRepository.getUsers()
            .filter { users -> users.isNotEmpty() }
            .switchIfEmpty(remoteRepository.getUsers().map { list ->
                localRepository.addUsers(list)
                list
            })
    }

    override fun getAlbums(id: Long): Single<List<Album>> {
        return localRepository.getAlbums(id)
            .filter { albums -> albums.isNotEmpty() }
            .switchIfEmpty(remoteRepository.getAlbums(id).map { list ->
                localRepository.addAlbums(list)
                list
            })
    }

    override fun getPhotos(id: Long): Single<List<Photo>> {
        return localRepository.getPhotos(id)
            .filter { photos -> photos.isNotEmpty() }
            .switchIfEmpty(remoteRepository.getPhotos(id).map { list ->
                localRepository.addPhotos(list)
                list
            })
    }
}