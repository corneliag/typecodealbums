package com.cjuca.typecodealbums.core.repository

import com.cjuca.typecodealbums.core.model.User
import io.reactivex.Single

class LocalRepository() : ILocalRepository {

    override fun getUsers(): Single<List<User>> {
        //TODO implement DATABASE
        return Single.just(emptyList())
    }
}