package com.cjuca.typecodealbums.core.manager

import com.cjuca.typecodealbums.core.model.User
import io.reactivex.Single

interface IAlbumsManager {

    fun getUsers(): Single<List<User>>
}