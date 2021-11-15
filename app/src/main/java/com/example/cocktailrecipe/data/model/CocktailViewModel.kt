package com.example.cocktailrecipe.data.model

import androidx.lifecycle.MutableLiveData
import com.example.cocktailrecipe.data.vo.CocatailVos

class CocktailViewModel : BaseViewModel() {

    //    private var _mCocktailList : MutableLiveData<CocatailVos> = MutableLiveData()
//    val mCocktailList : LiveData<CocatailVos> get() = _mCocktailList
    val catailListResponse: MutableLiveData<CocatailVos>
    val cocktailListDetailResponse: MutableLiveData<CocatailVos>

    init {
        super.initViewModel()
        catailListResponse = MutableLiveData()
        cocktailListDetailResponse = MutableLiveData()

    }

    fun getCocktailList() {
        mCompositeDisposable.add(
            BaseHolderModel.getObj().loadCocktails(
                catailListResponse,
                mErrorLD
            )
        )
    }

    fun getCocktailListDetail(cocktailId : String ){
        mCompositeDisposable.add(
            BaseHolderModel.getObj().loadCocktailDetail(

                cocktailListDetailResponse,
                mErrorLD,
                cocktailId
            )
        )
    }

}

