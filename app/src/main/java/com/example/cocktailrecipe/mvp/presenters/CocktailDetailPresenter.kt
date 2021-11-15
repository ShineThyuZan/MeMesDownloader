package com.example.cocktailrecipe.mvp.presenters

import com.example.cocktailrecipe.data.model.RetrofitProvider
import com.example.cocktailrecipe.data.vo.CocatailVos
import com.example.cocktailrecipe.mvp.views.CocktailDetailView

//class CocktailDetailPresenter : BasePresenter<CocktailDetailView>() {
//
//    private lateinit var mView: CocktailDetailView
//    private lateinit var mLiveData: MutableLiveData<CocatailVos>
//
//    override fun initPresenter(view: CocktailDetailView) {
//        super.initPresenter(view)
//        this.mView = view
//        mLiveData = MutableLiveData()
//
//
//    }
//
//    fun getLiveData(): MutableLiveData<CocatailVos> {
//        return mLiveData
//    }
//
//    fun onUiReady(cocktailId: String) {
//        RetrofitProvider.getObj().loadCocktailDetail(mLiveData, mErrorLD, cocktailId)
//    }
//

//}