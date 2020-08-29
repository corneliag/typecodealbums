package com.cjuca.typecodealbums.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cjuca.typecodealbums.R
import com.cjuca.typecodealbums.users.data.EmptyItem
import com.cjuca.typecodealbums.users.data.ItemViewType
import com.cjuca.typecodealbums.users.data.UserRecyclerItem
import com.cjuca.typecodealbums.users.data.UserUiItem
import com.cjuca.typecodealbums.users.viewholder.EmptyRecyclerViewHolder
import com.cjuca.typecodealbums.users.viewholder.UserRecyclerViewHolder

class UsersAdapter(
    private val onClick: (id: Long) -> Unit = {}
) :
    ListAdapter<UserRecyclerItem, RecyclerView.ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<UserRecyclerItem>() {

            override fun areItemsTheSame(
                oldItem: UserRecyclerItem,
                newItem: UserRecyclerItem
            ): Boolean {
                if (oldItem.getType() == newItem.getType()) {
                    return oldItem.isSameItem(newItem)
                }
                return false
            }

            override fun areContentsTheSame(
                oldItem: UserRecyclerItem,
                newItem: UserRecyclerItem
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
                    .inflate(R.layout.viewholder_user, parent, false)
                UserRecyclerViewHolder(view, onClick)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val data = getItem(position)) {
            is EmptyItem -> {
                val emptyTask = holder as EmptyRecyclerViewHolder
                emptyTask.bind(data)
            }
            is UserUiItem -> {
                (holder as UserRecyclerViewHolder).bind(data)
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