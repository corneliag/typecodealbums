package com.cjuca.typecodealbums.photos.viewholder

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.cjuca.typecodealbums.R
import com.cjuca.typecodealbums.base.bind
import com.cjuca.typecodealbums.photos.data.PhotoUiItem

class PhotoRecyclerViewHolder(itemView: View, private val onClick: (url: String) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    private val titleView by itemView.bind<TextView>(R.id.titleView)
    private val imageView by itemView.bind<AppCompatImageView>(R.id.imageView)

    fun bind(item: PhotoUiItem) {
        titleView.text = item.title
        if (item.thumbnailUrl.isEmpty()) {
            imageView.setImageResource(R.drawable.ic_placeholder)
        } else {
            val url = GlideUrl(
                item.thumbnailUrl, LazyHeaders.Builder()
                    .addHeader("User-Agent", "your-user-agent")
                    .build()
            )
            Glide.with(itemView.context).load(url)
                .apply(
                    RequestOptions().placeholder(R.drawable.ic_placeholder)
                        .error(R.drawable.ic_placeholder)
                )
                .into(imageView)
        }
        itemView.setOnClickListener {
            onClick.invoke(item.url)
        }
    }
}
