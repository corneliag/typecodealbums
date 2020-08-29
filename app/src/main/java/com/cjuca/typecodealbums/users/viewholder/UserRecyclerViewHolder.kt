package com.cjuca.typecodealbums.users.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cjuca.typecodealbums.R
import com.cjuca.typecodealbums.base.bind
import com.cjuca.typecodealbums.users.data.UserUiItem

class UserRecyclerViewHolder(itemView: View, private val onClick: (id: Long) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    private val nameView by itemView.bind<TextView>(R.id.nameView)
    private val usernameView by itemView.bind<TextView>(R.id.usernameView)
    private val emailView by itemView.bind<TextView>(R.id.emailView)
    private val phoneView by itemView.bind<TextView>(R.id.phoneView)
    private val websiteView by itemView.bind<TextView>(R.id.websiteView)

    fun bind(item: UserUiItem) {
        nameView.text = item.name
        usernameView.text = item.username
        emailView.text = item.email
        phoneView.text = item.phone
        websiteView.text = item.website
        itemView.setOnClickListener {
            onClick.invoke(item.id)
        }
    }
}
