package com.cjuca.typecodealbums.albums.viewholder

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cjuca.typecodealbums.R
import com.cjuca.typecodealbums.albums.adapter.AlbumsAdapter
import com.cjuca.typecodealbums.albums.viewmodel.AlbumsViewModel
import com.cjuca.typecodealbums.base.bind
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AlbumsViewHolder(
    private val rootView: View,
    private val viewModel: AlbumsViewModel,
    private val onClick: (Long) -> Unit = {}
) {
    private val recyclerView by rootView.bind<RecyclerView>(R.id.recyclerView)
    private val compositeDisposable = CompositeDisposable()
    private val albumsAdapter by lazy {
        AlbumsAdapter {
            onClick.invoke(it)
        }
    }

    init {
        recyclerView.layoutManager = LinearLayoutManager(rootView.context)
        recyclerView.adapter = albumsAdapter

        ContextCompat.getDrawable(rootView.context, R.drawable.decorator_divider)
            ?.let { drawable ->
                drawable.setTint(
                    ContextCompat.getColor(rootView.context,
                    R.color.grey))
                val decor = DividerItemDecoration(rootView.context,
                    DividerItemDecoration.VERTICAL)
                decor.setDrawable(drawable)
                recyclerView.addItemDecoration(decor)
            }

        compositeDisposable.add(viewModel.dataSource.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { uiData ->
                albumsAdapter.submitList(uiData.albumList)
            })
    }
}

