package com.cjuca.typecodealbums.albums.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cjuca.typecodealbums.R
import com.cjuca.typecodealbums.albums.data.AlbumUiItem
import com.cjuca.typecodealbums.base.bind

class AlbumRecyclerViewHolder(itemView: View, private val onClick: (id: Long) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    private val nameView by itemView.bind<TextView>(R.id.nameView)

    fun bind(item: AlbumUiItem) {
        nameView.text = item.title
        itemView.setOnClickListener {
            onClick.invoke(item.id)
        }
    }
}
