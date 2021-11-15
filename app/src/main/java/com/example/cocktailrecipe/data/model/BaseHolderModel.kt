package com.example.cocktailrecipe.data.model


import androidx.lifecycle.MutableLiveData
import com.example.cocktailrecipe.data.vo.CocatailVos
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

    fun loadCocktails(
        mCocatailLD: MutableLiveData<CocatailVos>, mErrorLD: MutableLiveData<String>
    ): Disposable {
        return mTheApi.loadCocktailList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mCocatailLD.value = it
            },
                {
                    mErrorLD.value = it.localizedMessage
                })
    }

    fun loadCocktailDetail(
        mCocatailLD: MutableLiveData<CocatailVos>,
        mErrorLD: MutableLiveData<String>,
        cocktailId: String
    ): Disposable {
        val params = HashMap<String, String>()
        params["i"] = cocktailId
        return mTheApi.loadCocktailDetails(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mCocatailLD.value = it
            },
                {
                    mErrorLD.value = it.localizedMessage
                })
    }


}