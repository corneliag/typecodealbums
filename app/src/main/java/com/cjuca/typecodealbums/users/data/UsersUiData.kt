package com.cjuca.typecodealbums.users.data

import androidx.annotation.StringRes
import com.cjuca.typecodealbums.base.RecyclerItem
import java.util.*

enum class ItemViewType(val type: Int) {
    EMPTY_STATE_VIEW(1), DISPLAY_VIEW(2);

    companion object {
        fun valueOf(value: Int): ItemViewType = values().find { it.type == value }!!
    }
}

data class UsersUiData(val userList: List<UserRecyclerItem>)

sealed class UserRecyclerItem : RecyclerItem

data class UserUiItem(
    val id: Long,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String
) : UserRecyclerItem() {
    override fun getUniqueId(): Long = id
    override fun isSameItem(
        other: RecyclerItem
    ): Boolean = other is UserUiItem && other.id == id

    override fun isSameContent(other: RecyclerItem): Boolean = other is UserUiItem && other == this

    override fun getType(): Int = ItemViewType.DISPLAY_VIEW.type
}

data class EmptyItem(@StringRes val title: Int) : UserRecyclerItem() {
    private val id: Long = UUID.randomUUID().mostSignificantBits
    override fun getUniqueId(): Long = id
    override fun getType(): Int = ItemViewType.EMPTY_STATE_VIEW.type
    override fun isSameItem(other: RecyclerItem): Boolean = other is EmptyItem
    override fun isSameContent(other: RecyclerItem): Boolean = other == this
}