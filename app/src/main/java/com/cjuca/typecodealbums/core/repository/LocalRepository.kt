package com.cjuca.typecodealbums.core.repository

import com.cjuca.typecodealbums.core.dao.AlbumDao
import com.cjuca.typecodealbums.core.dao.PhotoDao
import com.cjuca.typecodealbums.core.dao.UserDao
import com.cjuca.typecodealbums.core.model.Album
import com.cjuca.typecodealbums.core.model.Photo
import com.cjuca.typecodealbums.core.model.User
import io.reactivex.Completable
import io.reactivex.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LocalRepository(
    private val userDao: UserDao,
    private val albumDao: AlbumDao,
    private val photoDao: PhotoDao
) : ILocalRepository {

    override fun getUsers(): Single<List<User>> {
        return Single.fromCallable {
            userDao.getUsers()
        }
    }

    override fun getAlbums(id: Long): Single<List<Album>> {
        return Single.fromCallable {
            albumDao.getAlbums(id)
        }
    }

    override fun getPhotos(id: Long): Single<List<Photo>> {
        return Single.fromCallable {
            photoDao.getPhotos(id)
        }
    }

    override fun addUsers(userList: List<User>): Job {
        return GlobalScope.launch(Dispatchers.IO) {
            userList.map {
                userDao.insert(it)
            }
        }
    }

    override fun addAlbums(albumList: List<Album>): Job {
        return GlobalScope.launch(Dispatchers.IO) {
            albumList.map {
                albumDao.insert(it)
            }

        }
    }

    override fun addPhotos(photoList: List<Photo>): Job {
        return GlobalScope.launch(Dispatchers.IO) {
            photoList.map {
                photoDao.insert(it)
            }

        }
    }

    override fun clearTable(): Completable {
        return Completable.fromAction {
            GlobalScope.launch(Dispatchers.IO) {
                photoDao.deleteAll()
                albumDao.deleteAll()
                userDao.deleteAll()
            }
        }
    }

}