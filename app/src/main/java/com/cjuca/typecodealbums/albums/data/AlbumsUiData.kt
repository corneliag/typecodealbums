package com.cjuca.typecodealbums.albums.data

import androidx.annotation.StringRes
import com.cjuca.typecodealbums.base.RecyclerItem
import com.cjuca.typecodealbums.users.data.ItemViewType
import java.util.*

data class AlbumsUiData(val albumList: List<AlbumRecyclerItem>)

sealed class AlbumRecyclerItem : RecyclerItem

data class AlbumUiItem(
    val id: Long,
    val title: String
) : AlbumRecyclerItem() {
    override fun getUniqueId(): Long = id
    override fun isSameItem(
        other: RecyclerItem
    ): Boolean = other is AlbumUiItem && other.id == id

    override fun isSameContent(other: RecyclerItem): Boolean =
        other is AlbumRecyclerItem && other == this

    override fun getType(): Int = ItemViewType.DISPLAY_VIEW.type
}

data class EmptyItem(@StringRes val title: Int) : AlbumRecyclerItem() {
    private val id: Long = UUID.randomUUID().mostSignificantBits
    override fun getUniqueId(): Long = id
    override fun getType(): Int = ItemViewType.EMPTY_STATE_VIEW.type
    override fun isSameItem(other: RecyclerItem): Boolean = other is EmptyItem
    override fun isSameContent(other: RecyclerItem): Boolean = other == this
}