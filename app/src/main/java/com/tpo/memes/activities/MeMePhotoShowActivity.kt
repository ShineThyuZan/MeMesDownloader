package com.tpo.memes.activities

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tpo.memes.fragment.MeMePhotoListFragment
import com.tpo.memes.fragment.MeMePhotoPagerFragment
import com.tpo.memes.R
import kotlin.system.exitProcess

class MeMePhotoShowActivity : BaseActivity() {
    private var doubleBackToExitPressedOnce = false
    private var fragPhotoList: Fragment = MeMePhotoListFragment()
    private var fragPhotoPagerList: Fragment = MeMePhotoPagerFragment()
    private lateinit var navView: BottomNavigationView

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navi_meme -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragPhotoList)
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navi_pager -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, fragPhotoPagerList).commit()

                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activit_meme_list)

        supportFragmentManager.beginTransaction().add(R.id.container, fragPhotoList).commit()

        this.findViewById<ImageView>(R.id.ivNav)
            .setOnClickListener {
                findViewById<DrawerLayout>(R.id.drawerLayout).openDrawer(GravityCompat.START)
            }
        navView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

    }

    override fun onBackPressed() {
        if (this.findViewById<DrawerLayout>(R.id.drawerLayout)
                .isDrawerOpen(GravityCompat.START)
        ) {
            this.findViewById<DrawerLayout>(R.id.drawerLayout).closeDrawer(GravityCompat.START)
        } else {
            if (doubleBackToExitPressedOnce) {
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
            finish()
        }
        dialog.show()
    }
}