package com.example.cocktailrecipe.mvp.presenters

import android.arch.lifecycle.MutableLiveData
import com.example.cocktailrecipe.data.model.CocatailModel
import com.example.cocktailrecipe.data.vo.CocatailVo
import com.example.cocktailrecipe.data.vo.CocatailVos
import com.example.cocktailrecipe.delegate.CocatailDelegate
import com.example.cocktailrecipe.mvp.views.CocatailListView

open class CocatailListPresenter : CocatailDelegate, BasePresenter<CocatailListView>() {


    private lateinit var mView: CocatailListView
    private lateinit var mLiveData: MutableLiveData<CocatailVos>

    override fun initPresenter(view: CocatailListView) {
        super.initPresenter(view)
        this.mView = view
        mLiveData = MutableLiveData()


        CocatailModel.getObj().loadCocktails(mLiveData, mErrorLD)
    }


    fun getLiveData(): MutableLiveData<CocatailVos> {
        return mLiveData
    }

    override fun onTapCocatail(cocatailVo: CocatailVo) {
        mView.displaycocktailDetails(cocatailVo)
    }


}