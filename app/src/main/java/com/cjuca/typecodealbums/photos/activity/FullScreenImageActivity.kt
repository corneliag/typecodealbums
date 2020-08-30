package com.cjuca.typecodealbums.photos.activity

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.cjuca.typecodealbums.R
import com.cjuca.typecodealbums.base.bind

class FullscreenImageActivity : AppCompatActivity() {

    companion object {
        private const val INTENT_IMAGE_URL = "INTENT_IMAGE_URL"

        fun start(context: Context, imageUrl: String?, view: View) {
            if (imageUrl.isNullOrEmpty()) {
                Log.w("FullscreenImageActivity", "Impossible to open an image with an empty URL")
                return
            }
            val intent = Intent(context, FullscreenImageActivity::class.java)
            intent.putExtra(INTENT_IMAGE_URL, imageUrl)
            val options = ActivityOptions.makeSceneTransitionAnimation(
                context as Activity, view,
                context.getString(R.string.image_content_description)
            )
            context.startActivity(intent, options.toBundle())
        }
    }

    private val imageView: AppCompatImageView by bind(R.id.imageView)
    private val closeView: View by bind(R.id.closeView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)
        if (intent != null) {
            val url = GlideUrl(
                intent.getStringExtra(INTENT_IMAGE_URL), LazyHeaders.Builder()
                    .addHeader("User-Agent", "your-user-agent")
                    .build()
            )
            Glide.with(this).load(url)
                .apply(
                    RequestOptions().placeholder(R.drawable.ic_placeholder)
                        .error(R.drawable.ic_placeholder)
                )
                .into(imageView)
        } else {
            finish()
        }
        imageView.setOnClickListener { onBackPressed() }
        closeView.setOnClickListener { onBackPressed() }
    }
}