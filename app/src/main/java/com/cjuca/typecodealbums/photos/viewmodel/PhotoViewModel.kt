package com.cjuca.typecodealbums.photos.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.cjuca.typecodealbums.core.manager.IAlbumsManager
import com.cjuca.typecodealbums.photos.data.PhotosUiData
import com.cjuca.typecodealbums.photos.data.PhotosUiDataMapper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.schedulers.Schedulers

class PhotoViewModel(private val albumId: Long, private val manager: IAlbumsManager) : ViewModel() {

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }
    val dataSource: BehaviorProcessor<PhotosUiData> by lazy {
        BehaviorProcessor.create()
    }

    init {
        fetchPhotos()
    }

    fun fetchPhotos() {
        compositeDisposable.add(
            manager.getPhotos(albumId).map {
                PhotosUiDataMapper().mapToUiData(it)
            }.subscribeOn(Schedulers.io())
                .subscribe({ photos ->
                    dataSource.onNext(PhotosUiData(photos))
                }, { throwable ->
                    Log.e("PhotoViewModel", "Failed to get photos " + albumId, throwable)
                    dataSource.onNext(PhotosUiData(emptyList()))
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}