package com.cjuca.typecodealbums.core.repository

import com.cjuca.typecodealbums.core.model.User
import io.reactivex.Single

interface ILocalRepository {
    fun getUsers(): Single<List<User>>
}
