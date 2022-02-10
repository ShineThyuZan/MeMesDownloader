package com.example.cocktailrecipe.activities

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.cocktailrecipe.R
import com.google.android.gms.ads.*
import java.util.*

class FullScreenImageActivity : BaseActivity() {
    private var mAdView : AdView? = null

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


//    private fun loadAd() {
//        var adRequest = AdRequest.Builder().build()
//
//        InterstitialAd.load(
//            this, AD_UNIT_ID, adRequest,
//            object : InterstitialAdLoadCallback() {
//                override fun onAdFailedToLoad(adError: LoadAdError) {
//
//                    mInterstitialAd = null
//                    mAdIsLoading = false
//                    val error = "domain: ${adError.domain}, code: ${adError.code}, " +
//                            "message: ${adError.message}"
//                    Toast.makeText(
//                        this@FullScreenImageActivity,
//                        "onAdFailedToLoad() with error $error",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//
//                override fun onAdLoaded(interstitialAd: InterstitialAd) {
//                    mInterstitialAd = interstitialAd
//                    mAdIsLoading = false
//
//                    }
//            }
//        )
//    }
//
//    private fun createTimer(milliseconds: Long) {
//        mCountDownTimer?.cancel()
//
//        mCountDownTimer = object : CountDownTimer(milliseconds, 50) {
//            override fun onTick(millisUntilFinished: Long) {
//                mTimerMilliseconds = millisUntilFinished
//              //  timer.text = "seconds remaining: ${ millisUntilFinished / 1000 + 1 }"
//            }
//
//            override fun onFinish() {
//                mGameIsInProgress = false
////                timer.text = "done!"
////                retry_button.visibility = View.VISIBLE
//            }
//        }
//    }
//
//    // Show the ad if it's ready. Otherwise toast and restart the game.
//    private fun showInterstitial() {
//        if (mInterstitialAd != null) {
//            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
//                override fun onAdDismissedFullScreenContent() {
//
//                    // Don't forget to set the ad reference to null so you
//                    // don't show the ad a second time.
//                    mInterstitialAd = null
//                    loadAd()
//                }
//
//                override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
//
//                    // Don't forget to set the ad reference to null so you
//                    // don't show the ad a second time.
//                    mInterstitialAd = null
//                }
//
//                override fun onAdShowedFullScreenContent() {
//
//                    // Called when ad is dismissed.
//                }
//            }
//            mInterstitialAd?.show(this)
//        } else {
//            Toast.makeText(this, "Ad wasn't loaded.", Toast.LENGTH_SHORT).show()
//            startGame()
//        }
//
//    }
//
//    private fun startGame() {
//        if (!mAdIsLoading && mInterstitialAd == null) {
//            mAdIsLoading = true
//            loadAd()
//        }
//
//     //   retry_button.visibility = View.INVISIBLE
//        resumeGame(GAME_LENGTH_MILLISECONDS)
//    }
//
//    private fun resumeGame(milliseconds: Long) {
//        // Create a new timer for the correct length and start it.
//        mGameIsInProgress = true
//        mTimerMilliseconds = milliseconds
//        createTimer(milliseconds)
//        mCountDownTimer?.start()
//    }
//
//    // Resume the game if it's in progress.
//    public override fun onResume() {
//        super.onResume()
//
//        if (mGameIsInProgress) {
//            resumeGame(mTimerMilliseconds)
//        }
//    }
//
//    // Cancel the timer if the game is paused.
//    public override fun onPause() {
//        mCountDownTimer?.cancel()
//        super.onPause()
//    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


}
