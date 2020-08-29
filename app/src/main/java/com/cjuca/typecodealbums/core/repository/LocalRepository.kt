package com.cjuca.typecodealbums.core.repository

import com.cjuca.typecodealbums.core.model.Album
import com.cjuca.typecodealbums.core.model.User
import io.reactivex.Single

class LocalRepository() : ILocalRepository {

    override fun getUsers(): Single<List<User>> {
        //TODO implement DATABASE
        return Single.just(emptyList())
    }

    override fun getAlbums(id: Long): Single<List<Album>> {
        //TODO implement DATABASE
        return Single.just(emptyList())
    }
}