package com.cjuca.typecodealbums

import com.cjuca.typecodealbums.albums.viewmodel.AlbumsViewModel
import com.cjuca.typecodealbums.core.api.ApiClient
import com.cjuca.typecodealbums.core.dao.AlbumDao
import com.cjuca.typecodealbums.core.dao.PhotoDao
import com.cjuca.typecodealbums.core.dao.UserDao
import com.cjuca.typecodealbums.core.database.AlbumsRoomDatabase
import com.cjuca.typecodealbums.core.manager.AlbumsManager
import com.cjuca.typecodealbums.core.manager.IAlbumsManager
import com.cjuca.typecodealbums.core.repository.*
import com.cjuca.typecodealbums.photos.viewmodel.PhotoViewModel
import com.cjuca.typecodealbums.users.viewmodel.UsersViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val database = AlbumsRoomDatabase

val daoModule = module {
    single<UserDao> { database.getDatabase(androidApplication()).userDao() }
    single<AlbumDao> { database.getDatabase(androidApplication()).albumDao() }
    single<PhotoDao> { database.getDatabase(androidApplication()).photoDao() }
}

val appModule = module {
    single<ApiClient> { ApiClient(androidApplication()) }
    single<ILocalRepository> { LocalRepository(get(), get(), get()) }
    single<IRemoteRepository> { RemoteRepository(get()) }
    single<IAlbumsRepository> { AlbumsRepository(get(), get()) }
    single<IAlbumsManager> { AlbumsManager(get()) }
}

val appViewModel = module {
    viewModel { UsersViewModel(get()) }
    viewModel { (userId: Long) -> AlbumsViewModel(userId, get()) }
    viewModel { (albumId: Long) -> PhotoViewModel(albumId, get()) }
}