package com.cjuca.typecodealbums.photos.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cjuca.typecodealbums.R
import com.cjuca.typecodealbums.base.bind
import com.cjuca.typecodealbums.photos.viewholder.PhotosViewHolder
import com.cjuca.typecodealbums.photos.viewmodel.PhotoViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import kotlin.properties.Delegates

class PhotosActivity : AppCompatActivity(R.layout.activity_photos) {

    companion object {
        private const val ALBUM_ID = "albumId"

        fun start(context: Context, albumId: Long) {
            val intent = Intent(context, PhotosActivity::class.java)
            intent.putExtra(ALBUM_ID, albumId)
            context.startActivity(intent)
        }
    }

    private var albumId: Long = -1
    private var viewHolder by Delegates.notNull<PhotosViewHolder>()
    private val viewModel: PhotoViewModel by viewModel { parametersOf(albumId) }
    private val contentView by bind<View>(R.id.contentView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.extras?.let { extras ->
            albumId = extras.getLong(ALBUM_ID, -1)
        }
        viewHolder = PhotosViewHolder(contentView, viewModel) {
            FullscreenImageActivity.start(this, it, contentView)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.toolbar_photos_title)
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