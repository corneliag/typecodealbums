package com.cjuca.typecodealbums

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cjuca.typecodealbums.base.bind
import com.cjuca.typecodealbums.users.viewholder.UsersViewHolder
import com.cjuca.typecodealbums.users.viewmodel.UsersViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private var viewHolder by Delegates.notNull<UsersViewHolder>()
    private val viewModel: UsersViewModel by viewModel()

    private val contentView by bind<View>(R.id.contentView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewHolder = UsersViewHolder(contentView, viewModel) {
            //TODO
        }
    }
}