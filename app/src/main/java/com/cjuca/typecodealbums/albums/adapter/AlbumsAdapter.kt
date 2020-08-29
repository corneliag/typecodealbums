package com.cjuca.typecodealbums.albums.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cjuca.typecodealbums.R
import com.cjuca.typecodealbums.albums.data.AlbumRecyclerItem
import com.cjuca.typecodealbums.albums.data.AlbumUiItem
import com.cjuca.typecodealbums.albums.data.EmptyItem
import com.cjuca.typecodealbums.albums.viewholder.AlbumRecyclerViewHolder
import com.cjuca.typecodealbums.albums.viewholder.EmptyRecyclerViewHolder
import com.cjuca.typecodealbums.users.data.ItemViewType

class AlbumsAdapter(
    private val onClick: (id: Long) -> Unit = {}
) :
    ListAdapter<AlbumRecyclerItem, RecyclerView.ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<AlbumRecyclerItem>() {

            override fun areItemsTheSame(
                oldItem: AlbumRecyclerItem,
                newItem: AlbumRecyclerItem
            ): Boolean {
                if (oldItem.getType() == newItem.getType()) {
                    return oldItem.isSameItem(newItem)
                }
                return false
            }

            override fun areContentsTheSame(
                oldItem: AlbumRecyclerItem,
                newItem: AlbumRecyclerItem
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
                    .inflate(R.layout.viewholder_album, parent, false)
                AlbumRecyclerViewHolder(view, onClick)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val data = getItem(position)) {
            is EmptyItem -> {
                val emptyTask = holder as EmptyRecyclerViewHolder
                emptyTask.bind(data)
            }
            is AlbumUiItem -> {
                (holder as AlbumRecyclerViewHolder).bind(data)
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