package com.tpo.memes.data.model


import androidx.lifecycle.MutableLiveData
import com.tpo.memes.data.vo.MeMePagerResponse
import com.tpo.memes.data.vo.MeMeResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class BaseHolderModel : RetrofitProvider() {
    companion object {
        private var instance: BaseHolderModel? = null


        fun getObj(): BaseHolderModel {
            if (instance == null) {
                instance = BaseHolderModel()
            }
            val i = instance
            return i!!
        }
    }

    fun getMeMeListPhoto(
        mMemeResponse: MutableLiveData<MeMeResponse>,
        mErrorLD: MutableLiveData<String>
    ): Disposable {
        return mTheApi.getMeMePhotoList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mMemeResponse.value = it
            },
                {
                    mErrorLD.value = it.localizedMessage
                })
    }

    fun getMeMePhotoPager(
        pageCount : Int ,
        mMemeResponse: MutableLiveData<MeMePagerResponse>,
        mErrorLD: MutableLiveData<String>
    ): Disposable {
        return mTheApi.getMeMePhotoPager(pageCount)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mMemeResponse.value = it
            },
                {
                    mErrorLD.value = it.localizedMessage
                })
    }

}