package com.cjuca.typecodealbums.photos.viewholder

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cjuca.typecodealbums.R
import com.cjuca.typecodealbums.base.bind
import com.cjuca.typecodealbums.photos.adapter.PhotosAdapter
import com.cjuca.typecodealbums.photos.viewmodel.PhotoViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PhotosViewHolder(
    rootView: View, viewModel: PhotoViewModel, private val onClick: (String) -> Unit = {}
) {

    companion object {
        private const val SPAN_COUNT = 2
    }

    private val recyclerView by rootView.bind<RecyclerView>(R.id.recyclerView)
    private val compositeDisposable = CompositeDisposable()
    private val adapter by lazy {
        PhotosAdapter {
            onClick.invoke(it)
        }
    }

    init {
        recyclerView.layoutManager = GridLayoutManager(rootView.context, SPAN_COUNT)
        recyclerView.adapter = adapter

        compositeDisposable.add(viewModel.dataSource.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { uiData ->
                adapter.submitList(uiData.photoList)
            })
    }
}

