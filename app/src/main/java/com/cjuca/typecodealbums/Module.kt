package com.cjuca.typecodealbums

import com.cjuca.typecodealbums.albums.viewmodel.AlbumsViewModel
import com.cjuca.typecodealbums.core.ApiService
import com.cjuca.typecodealbums.core.manager.AlbumsManager
import com.cjuca.typecodealbums.core.manager.IAlbumsManager
import com.cjuca.typecodealbums.core.repository.*
import com.cjuca.typecodealbums.users.viewmodel.UsersViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<ApiService> { ApiService.create(androidApplication()) }
    single<ILocalRepository> { LocalRepository() }
    single<IRemoteRepository> { RemoteRepository(get()) }
    single<IAlbumsRepository> { AlbumsRepository(get(), get()) }
    single<IAlbumsManager> { AlbumsManager(get()) }
}

val appViewModel = module {
    viewModel { UsersViewModel(get()) }
    viewModel { (userId: Long) -> AlbumsViewModel(userId, get()) }
}