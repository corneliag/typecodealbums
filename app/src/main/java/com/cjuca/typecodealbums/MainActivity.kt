package com.cjuca.typecodealbums

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.users_menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        if (searchItem != null) {
            val searchView = searchItem?.actionView as SearchView

            searchView.setOnCloseListener { true }
            val searchPlate =
                searchView.findViewById(androidx.appcompat.R.id.search_src_text) as EditText
            searchPlate.hint = "Search"
            val searchPlateView: View =
                searchView.findViewById(androidx.appcompat.R.id.search_plate)
            searchPlateView.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    android.R.color.transparent
                )
            )

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { viewModel.fetchUsers(it) }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let { viewModel.fetchUsers(it) }
                    return false
                }
            })

            val searchManager =
                getSystemService(Context.SEARCH_SERVICE) as SearchManager
            searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        return super.onCreateOptionsMenu(menu)
    }
}