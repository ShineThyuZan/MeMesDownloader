package com.tpo.cocktailrecipe.data.model

import androidx.lifecycle.MutableLiveData
import com.tpo.cocktailrecipe.data.vo.MeMePagerResponse
import com.tpo.cocktailrecipe.data.vo.MeMeResponse

class CocktailViewModel : BaseViewModel() {
    val memeListResponse: MutableLiveData<MeMeResponse>
    val memePagerListResponse: MutableLiveData<MeMePagerResponse>

    init {
        super.initViewModel()
        memeListResponse = MutableLiveData()
        memePagerListResponse = MutableLiveData()

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
}

