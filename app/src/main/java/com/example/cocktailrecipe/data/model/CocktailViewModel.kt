package com.example.cocktailrecipe.data.model

import androidx.lifecycle.MutableLiveData
import com.example.cocktailrecipe.data.vo.CocktailResponse
import com.example.cocktailrecipe.data.vo.MeMeResponse

class CocktailViewModel : BaseViewModel() {

    //    private var _mCocktailList : MutableLiveData<CocatailVos> = MutableLiveData()
//    val mCocktailList : LiveData<CocatailVos> get() = _mCocktailList
    val catailListResponse: MutableLiveData<CocktailResponse>
    val cocktailListDetailResponse: MutableLiveData<CocktailResponse>
    val memeListResponse : MutableLiveData<MeMeResponse>

    init {
        super.initViewModel()
        catailListResponse = MutableLiveData()
        cocktailListDetailResponse = MutableLiveData()
        memeListResponse = MutableLiveData()

    }

    fun getCocktailList() {
        mCompositeDisposable.add(
            BaseHolderModel.getObj().loadCocktails(
                catailListResponse,
                mErrorLD
            )
        )
    }
    fun getMeMeListPhoto() {
        mCompositeDisposable.add(
            BaseHolderModel.getObj().getMeMeListPhoto(
                memeListResponse,
                mErrorLD
            )
        )
    }

    fun getCocktailListDetail(cocktailId: String) {
        mCompositeDisposable.add(
            BaseHolderModel.getObj().loadCocktailDetail(
                cocktailId,
                cocktailListDetailResponse,
                mErrorLD

            )
        )
    }



}

