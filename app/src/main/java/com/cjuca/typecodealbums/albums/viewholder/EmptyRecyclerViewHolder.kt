package com.cjuca.typecodealbums.albums.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cjuca.typecodealbums.R
import com.cjuca.typecodealbums.albums.data.EmptyItem
import com.cjuca.typecodealbums.base.bind

class EmptyRecyclerViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    private val textView by itemView.bind<TextView>(R.id.text)

    fun bind(item: EmptyItem) {
        textView.text = itemView.context.getString(item.title)
    }
}
