package com.cjuca.typecodealbums.albums.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.cjuca.typecodealbums.albums.data.AlbumsUiData
import com.cjuca.typecodealbums.albums.data.AlbumsUiDataMapper
import com.cjuca.typecodealbums.core.manager.IAlbumsManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.schedulers.Schedulers

class AlbumsViewModel(private val userId: Long, private val manager: IAlbumsManager) : ViewModel() {

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }
    val dataSource: BehaviorProcessor<AlbumsUiData> by lazy {
        BehaviorProcessor.create()
    }

    init {
        fetchAlbums()
    }

    fun fetchAlbums() {
        compositeDisposable.add(
            manager.getAlbums(userId).map {
                AlbumsUiDataMapper().mapToUiData(it)
            }.subscribeOn(Schedulers.io())
                .subscribe({ albums ->
                    dataSource.onNext(AlbumsUiData(albums))
                }, { throwable ->
                    Log.e("AlbumsViewModel", "Failed to get albums $userId", throwable)
                    dataSource.onNext(AlbumsUiData(emptyList()))
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}