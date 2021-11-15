package com.example.cocktailrecipe.mvp.presenters

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

abstract class BasePresenter<T> : ViewModel() {
    protected lateinit var mErrorLD: MutableLiveData<String>

    private var mView: T? = null
    open fun initPresenter(view: T) {
        this.mView = view
        mErrorLD = MutableLiveData()
    }

    fun getErrorLD(): MutableLiveData<String> {
        return mErrorLD
    }
}