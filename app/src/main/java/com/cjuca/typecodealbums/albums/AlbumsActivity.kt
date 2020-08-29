package com.cjuca.typecodealbums.albums

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cjuca.typecodealbums.R
import com.cjuca.typecodealbums.albums.viewholder.AlbumsViewHolder
import com.cjuca.typecodealbums.albums.viewmodel.AlbumsViewModel
import com.cjuca.typecodealbums.base.bind
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import kotlin.properties.Delegates

class AlbumsActivity : AppCompatActivity(R.layout.activity_albums) {

    companion object {
        private const val USER_ID = "userId"

        fun start(context: Context, userId: Long) {
            val intent = Intent(context, AlbumsActivity::class.java)
            intent.putExtra(USER_ID, userId)
            context.startActivity(intent)
        }
    }

    private var userId: Long = -1
    private var viewHolder by Delegates.notNull<AlbumsViewHolder>()
    private val viewModel: AlbumsViewModel by viewModel { parametersOf(userId) }
    private val contentView by bind<View>(R.id.contentView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.extras?.let { extras ->
            userId = extras.getLong(USER_ID, -1)
        }
        viewHolder = AlbumsViewHolder(contentView, viewModel) {
            //TODO
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.toolbar_albums_title)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}