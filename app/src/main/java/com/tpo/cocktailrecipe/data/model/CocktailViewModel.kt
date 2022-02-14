package com.tpo.cocktailrecipe.data.model

import androidx.lifecycle.MutableLiveData
import com.tpo.cocktailrecipe.data.vo.CocktailResponse
import com.tpo.cocktailrecipe.data.vo.MeMePagerResponse
import com.tpo.cocktailrecipe.data.vo.MeMeResponse

class CocktailViewModel : BaseViewModel() {
    //private var _mCocktailList : MutableLiveData<CocatailVos> = MutableLiveData()
//    val mCocktailList : LiveData<CocatailVos> get() = _mCocktailList
    val catailListResponse: MutableLiveData<CocktailResponse>
    val cocktailListDetailResponse: MutableLiveData<CocktailResponse>
    val memeListResponse: MutableLiveData<MeMeResponse>
    val memePagerListResponse: MutableLiveData<MeMePagerResponse>

    init {
        super.initViewModel()
        catailListResponse = MutableLiveData()
        cocktailListDetailResponse = MutableLiveData()
        memeListResponse = MutableLiveData()
        memePagerListResponse = MutableLiveData()

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

    fun getMeMePhotoPager(pageNo: Int) {
        mCompositeDisposable.add(
            BaseHolderModel.getObj().getMeMePhotoPager(
                pageNo,
                memePagerListResponse,
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

