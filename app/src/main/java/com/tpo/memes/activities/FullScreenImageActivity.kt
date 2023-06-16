package com.tpo.memes.activities

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.gms.ads.*
import com.tpo.memes.R

class FullScreenImageActivity : BaseActivity() {
    private var mAdView: AdView? = null

    companion object {
        private const val KEY_IMAGE_URL = "image-url"
        private const val KEY_NAME = "name"
        private const val KEY_RATING = "rating"
        private lateinit var imageUrl: String
        private lateinit var name: String
        private lateinit var rating: String
        fun newIntent(context: Context, imageUrl: String, name: String, rating: String): Intent {
            val intent = Intent(context, FullScreenImageActivity::class.java)
            intent.putExtra(KEY_IMAGE_URL, imageUrl)
            intent.putExtra(KEY_NAME, name)
            intent.putExtra(KEY_RATING, rating)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_image)

        imageUrl = intent!!.getStringExtra(KEY_IMAGE_URL).toString()
        name = intent!!.getStringExtra(KEY_NAME).toString()
        rating = intent!!.getStringExtra(KEY_RATING).toString()

        this.findViewById<TextView>(R.id.tvName).text = name
        this.findViewById<TextView>(R.id.tvRating).text = "Rating : $rating"
        val url = imageUrl
        val uri = Uri.parse(url)
        val ivFullImage = this.findViewById<ImageView>(R.id.ivFullScreen)
        Glide.with(this@FullScreenImageActivity)
            .load(uri)
            .into(ivFullImage)


        /** show banner google admob */
        MobileAds.initialize(this) {
            mAdView = findViewById(R.id.adView)
            val adRequest = AdRequest.Builder().build()
            mAdView!!.loadAd(adRequest)
        }

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
