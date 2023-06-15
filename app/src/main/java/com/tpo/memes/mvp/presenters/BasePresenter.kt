package com.tpo.memes.mvp.presenters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


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