package com.example.cocktailrecipe.data.model

import android.arch.lifecycle.MutableLiveData
import com.example.cocktailrecipe.data.vo.CocatailVos
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class CocatailModel : BaseModel() {
    companion object {
        private var instance: CocatailModel? = null


        fun getObj(): CocatailModel {
            if (instance == null) {
                instance = CocatailModel()
            }
            return instance as CocatailModel
        }

    }


    fun loadCocktails(
        mCocatailLD: MutableLiveData<CocatailVos>
        , mErrorLD: MutableLiveData<String>
    ) {


        mTheApi.loadCocktailList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<CocatailVos> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: CocatailVos) {
                    mCocatailLD.value = t
                }

                override fun onError(e: Throwable) {
                    mErrorLD.value = e.message
                }
            })

    }

    fun loadCocktailDetail(
        mCocatailLD: MutableLiveData<CocatailVos>,
        mErrorLD: MutableLiveData<String>,
        cocktailId: String
    ) {

        val params = HashMap<String, String>()
        params["i"] = cocktailId
        mTheApi.loadCocktailDetails(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<CocatailVos> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: CocatailVos) {
                    mCocatailLD.value = t
                }

                override fun onError(e: Throwable) {

                    mErrorLD.value = e.message
                }

            })
    }


}