package com.example.cocktailrecipe.activities

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity


open class BaseActivity : AppCompatActivity(), Observer<String> {
    override fun onChanged(t: String?) {
        displayErrorMsg(t!!)
    }

    protected open fun displayErrorMsg(errorMsg: String) {

    }

}