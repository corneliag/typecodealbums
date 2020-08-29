package com.cjuca.typecodealbums.users.data

import com.cjuca.typecodealbums.R
import com.cjuca.typecodealbums.core.model.User

class UsersUiDataMapper {

    fun mapToUiData(userList: List<User>, searchValue: String): List<UserRecyclerItem> {
        if (userList.isEmpty()) {
            return listOf(EmptyItem(R.string.empty_title_for_user))
        }
        if (searchValue.isNotEmpty()) {
            val filteredList = userList.filter {
                it.name.contains(searchValue, true) || it.username.contains(
                    searchValue,
                    true
                ) || it.email.contains(searchValue, true)
            }
            return filteredList.map {
                UserUiItem(it.id, it.name, it.username, it.email, it.phone, it.website)
            }
        }
        return userList.map {
            UserUiItem(it.id, it.name, it.username, it.email, it.phone, it.website)
        }
    }
}