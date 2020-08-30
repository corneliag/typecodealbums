package com.cjuca.typecodealbums.photos.data

import androidx.annotation.StringRes
import com.cjuca.typecodealbums.base.RecyclerItem
import com.cjuca.typecodealbums.users.data.ItemViewType
import java.util.*

data class PhotosUiData(val photoList: List<PhotoRecyclerItem>)

sealed class PhotoRecyclerItem : RecyclerItem

data class PhotoUiItem(
    val id: Long,
    val title: String, val thumbnailUrl: String, val url: String
) : PhotoRecyclerItem() {
    override fun getUniqueId(): Long = id
    override fun isSameItem(
        other: RecyclerItem
    ): Boolean = other is PhotoUiItem && other.id == id

    override fun isSameContent(other: RecyclerItem): Boolean =
        other is PhotoRecyclerItem && other == this

    override fun getType(): Int = ItemViewType.DISPLAY_VIEW.type
}

data class EmptyItem(@StringRes val title: Int) : PhotoRecyclerItem() {
    private val id: Long = UUID.randomUUID().mostSignificantBits
    override fun getUniqueId(): Long = id
    override fun getType(): Int = ItemViewType.EMPTY_STATE_VIEW.type
    override fun isSameItem(other: RecyclerItem): Boolean = other is EmptyItem
    override fun isSameContent(other: RecyclerItem): Boolean = other == this
}