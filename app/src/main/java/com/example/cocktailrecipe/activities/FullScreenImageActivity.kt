package com.example.cocktailrecipe.activities

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.cocktailrecipe.R
import java.util.*


class FullScreenImageActivity : BaseActivity() {
    companion object {
        private const val KEY_IMAGE_URL = "image-url"
        private lateinit var imageUrl: String
        fun newIntent(context: Context, imageUrl: String): Intent {
            val intent = Intent(context, FullScreenImageActivity::class.java)
            intent.putExtra(KEY_IMAGE_URL, imageUrl)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_image)


        imageUrl = intent!!.getStringExtra(KEY_IMAGE_URL).toString()
        val url = imageUrl
        val uri = Uri.parse(url)
        val ivFullImage = this.findViewById<ImageView>(R.id.ivFullScreen)
        Glide.with(this@FullScreenImageActivity)
            .load(uri)
            .into(ivFullImage)

        /** download imageUrl to phone storage */
        val download = this.findViewById<ImageView>(R.id.ivDownload)

        download.setOnClickListener {
            val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            val uri = Uri.parse(imageUrl)
            val request = DownloadManager.Request(uri)
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            val reference = downloadManager.enqueue(request)
            Toast.makeText(this, "Download Complete", Toast.LENGTH_SHORT).show()
        }

        /** onBack case */
        val backImage = this.findViewById<ImageView>(R.id.ivBack)
        backImage.setOnClickListener {
            onBackPressed()
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


}