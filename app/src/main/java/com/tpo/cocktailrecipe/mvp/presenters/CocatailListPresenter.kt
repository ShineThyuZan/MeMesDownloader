package com.tpo.cocktailrecipe.mvp.presenters

//open class CocatailListPresenter : CocatailDelegate, BasePresenter<CocatailListView>() {
//
//
//    private lateinit var mView: CocatailListView
//    private lateinit var mLiveData: MutableLiveData<CocatailVos>
//
//    override fun initPresenter(view: CocatailListView) {
//        super.initPresenter(view)
//        this.mView = view
//        mLiveData = MutableLiveData()
//
//
//        RetrofitProvider.getObj().loadCocktails(mLiveData, mErrorLD)
//    }
//
//
//    fun getLiveData(): MutableLiveData<CocatailVos> {
//        return mLiveData
//    }
//
//    override fun onTapCocatail(cocatailVo: CocatailVo) {
//        mView.displaycocktailDetails(cocatailVo)
//    }
//
//
//}