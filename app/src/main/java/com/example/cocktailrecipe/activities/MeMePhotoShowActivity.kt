package com.example.cocktailrecipe.activities

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailrecipe.R
import com.example.cocktailrecipe.adapters.MemePhotoListAdapter
import com.example.cocktailrecipe.data.model.CocktailViewModel
import com.example.cocktailrecipe.data.vo.MemeData
import com.example.cocktailrecipe.delegate.MeMePhotoItemDelegate
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlin.system.exitProcess

class MeMePhotoShowActivity : BaseActivity(), MeMePhotoItemDelegate {
    private lateinit var memePhotoListAdapter: MemePhotoListAdapter
    private lateinit var mViewModel: CocktailViewModel
    private var mAdView: AdView? = null
    private var doubleBackToExitPressedOnce = false
    private lateinit var memeDataList : MutableList<MemeData>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activit_meme_list)


        MobileAds.initialize(this) {
            mAdView = findViewById(R.id.adView)
            val adRequest = AdRequest.Builder().build()
            mAdView!!.loadAd(adRequest)
        }

        mViewModel = ViewModelProviders.of(this@MeMePhotoShowActivity)
            .get(CocktailViewModel::class.java)

        // create adapter
        memePhotoListAdapter = MemePhotoListAdapter(baseContext, this)

        // Api Call
        mViewModel.getMeMeListPhoto()

        //Api Response catch
        observe()

        this.findViewById<ImageView>(R.id.ivNav)
            .setOnClickListener {
                findViewById<DrawerLayout>(R.id.drawerLayout).openDrawer(GravityCompat.START)
            }

        this.findViewById<androidx.appcompat.widget.SearchView>(R.id.searchView).setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                val result = memeDataList.filter { data ->
                    data.name.contains(query!!)
                }
                memePhotoListAdapter.clearData()
                memePhotoListAdapter.appendNewData(result as MutableList<MemeData>)

                return true
            }

            override fun onQueryTextChange(inputSearchText: String?): Boolean {
                if(inputSearchText.isNullOrBlank()){
                    memePhotoListAdapter.setNewDataList(memeDataList)
                }
                return true
            }
        })
        findViewById<RecyclerView>(R.id.rvMeMeList).apply {
            adapter = memePhotoListAdapter
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
        }


    }

    private fun observe() {
        mViewModel.memeListResponse.observe(this, Observer {
             memeDataList = it.data.memes as MutableList<MemeData>
            memePhotoListAdapter.setNewDataList(it.data.memes as MutableList<MemeData>)
        })
        mViewModel.mErrorLD.observe(
            this, Observer {
                Toast.makeText(this, "In error ", Toast.LENGTH_SHORT).show()
            }
        )
    }

    override fun onBackPressed() {
        if (this.findViewById<DrawerLayout>(R.id.drawerLayout).isDrawerOpen(GravityCompat.START)) {
            this.findViewById<DrawerLayout>(R.id.drawerLayout).closeDrawer(GravityCompat.START)
        } else {
            if (doubleBackToExitPressedOnce) {
                finish()
                moveTaskToBack(true)
                exitProcess(-0)
            }

            this.doubleBackToExitPressedOnce = true
            showBackConfirmDialog()
            Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
        }
    }

    private fun showBackConfirmDialog() {
        val exitBtn: Button
        val cancelBtn: Button
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_exit_confirmation)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        /** animate dialog with window animation */
        dialog.window!!.setWindowAnimations(R.style.AnimationForDialog)
        dialog.setCanceledOnTouchOutside(false)
        exitBtn = dialog.findViewById(R.id.btnExit) as Button
        cancelBtn = dialog.findViewById(R.id.btnCancel) as Button

        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        exitBtn.setOnClickListener {
            //todo delete dataStore userAccessToken key and LoginState
            finish()
        }
        dialog.show()
    }


    override fun onMeMeItemClicked(memeVos: MemeData) {
        startActivity(FullScreenImageActivity.newIntent(this, memeVos.url))
    }
}