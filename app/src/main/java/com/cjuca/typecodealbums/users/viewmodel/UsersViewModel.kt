package com.cjuca.typecodealbums.users.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.cjuca.typecodealbums.core.manager.IAlbumsManager
import com.cjuca.typecodealbums.users.data.UsersUiData
import com.cjuca.typecodealbums.users.data.UsersUiDataMapper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.schedulers.Schedulers

class UsersViewModel(private val manager: IAlbumsManager) : ViewModel() {

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }
    val dataSource: BehaviorProcessor<UsersUiData> by lazy {
        BehaviorProcessor.create()
    }

    init {
        fetchUsers("")
    }

    fun fetchUsers(searchValue: String) {
        compositeDisposable.add(
            manager.getUsers().map {
                UsersUiDataMapper().mapToUiData(it, searchValue)
            }.subscribeOn(Schedulers.io())
                .subscribe({ users ->
                    dataSource.onNext(UsersUiData(users))
                }, { throwable ->
                    Log.e("UsersViewModel", "Failed to get users", throwable)
                    dataSource.onNext(UsersUiData(emptyList()))
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}