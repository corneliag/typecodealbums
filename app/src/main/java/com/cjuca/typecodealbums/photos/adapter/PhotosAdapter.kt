package com.cjuca.typecodealbums.photos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cjuca.typecodealbums.R
import com.cjuca.typecodealbums.photos.data.EmptyItem
import com.cjuca.typecodealbums.photos.data.PhotoRecyclerItem
import com.cjuca.typecodealbums.photos.data.PhotoUiItem
import com.cjuca.typecodealbums.photos.viewholder.EmptyRecyclerViewHolder
import com.cjuca.typecodealbums.photos.viewholder.PhotoRecyclerViewHolder
import com.cjuca.typecodealbums.users.data.ItemViewType

class PhotosAdapter(private val onClick: (url: String) -> Unit = {}) :
    ListAdapter<PhotoRecyclerItem, RecyclerView.ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<PhotoRecyclerItem>() {

            override fun areItemsTheSame(
                oldItem: PhotoRecyclerItem,
                newItem: PhotoRecyclerItem
            ): Boolean {
                if (oldItem.getType() == newItem.getType()) {
                    return oldItem.isSameItem(newItem)
                }
                return false
            }

            override fun areContentsTheSame(
                oldItem: PhotoRecyclerItem,
                newItem: PhotoRecyclerItem
            ): Boolean {
                return oldItem.isSameContent(newItem)
            }

        }
    }

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (ItemViewType.valueOf(viewType)) {
            ItemViewType.EMPTY_STATE_VIEW -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.viewholder_empty, parent, false)
                EmptyRecyclerViewHolder(view)
            }

            ItemViewType.DISPLAY_VIEW -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.viewholder_photo, parent, false)
                PhotoRecyclerViewHolder(view, onClick)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val data = getItem(position)) {
            is EmptyItem -> {
                val emptyTask = holder as EmptyRecyclerViewHolder
                emptyTask.bind(data)
            }
            is PhotoUiItem -> {
                (holder as PhotoRecyclerViewHolder).bind(data)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).getType()
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).getUniqueId()
    }
}