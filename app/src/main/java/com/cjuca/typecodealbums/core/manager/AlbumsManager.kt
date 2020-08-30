package com.cjuca.typecodealbums.core.manager

import com.cjuca.typecodealbums.core.model.Album
import com.cjuca.typecodealbums.core.model.Photo
import com.cjuca.typecodealbums.core.model.User
import com.cjuca.typecodealbums.core.repository.IAlbumsRepository
import io.reactivex.Single

class AlbumsManager(private val repository: IAlbumsRepository) : IAlbumsManager {

    override fun getUsers(): Single<List<User>> {
        return repository.getUsers()
    }

    override fun getAlbums(id: Long): Single<List<Album>> {
        return repository.getAlbums(id)
    }

    override fun getPhotos(id: Long): Single<List<Photo>> {
        return repository.getPhotos(id)
    }
}