package com.cjuca.typecodealbums.users.viewholder

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cjuca.typecodealbums.R
import com.cjuca.typecodealbums.base.bind
import com.cjuca.typecodealbums.users.adapter.UsersAdapter
import com.cjuca.typecodealbums.users.viewmodel.UsersViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UsersViewHolder(
    private val rootView: View,
    private val viewModel: UsersViewModel,
    private val onClick: (Long) -> Unit = {}
) {
    private val recyclerView by rootView.bind<RecyclerView>(R.id.recyclerView)
    private val compositeDisposable = CompositeDisposable()
    private val usersAdapter by lazy {
        UsersAdapter {
            onClick.invoke(it)
        }
    }

    init {
        recyclerView.layoutManager = LinearLayoutManager(rootView.context)
        recyclerView.adapter = usersAdapter

        compositeDisposable.add(viewModel.dataSource.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { uiData ->
                usersAdapter.submitList(uiData.userList)
            })
    }
}

