package com.cjuca.typecodealbums.users.data

import com.cjuca.typecodealbums.R
import com.cjuca.typecodealbums.core.model.User

class UsersUiDataMapper {

    fun mapToUiData(userList: List<User>): List<UserRecyclerItem> {
        if (userList.isEmpty()) {
            return listOf(EmptyItem(R.string.empty_title_for_user))
        }
        return userList.map {
            UserUiItem(it.id, it.name, it.username, it.email, it.phone, it.website)
        }
    }
}