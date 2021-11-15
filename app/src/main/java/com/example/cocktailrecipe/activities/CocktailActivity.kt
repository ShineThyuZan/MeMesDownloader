package com.example.cocktailrecipe.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.example.cocktailrecipe.R
import com.example.cocktailrecipe.adapters.CocktailAdapter
import com.example.cocktailrecipe.data.model.CocatailModel
import com.example.cocktailrecipe.data.vo.CocatailVo
import com.example.cocktailrecipe.data.vo.CocatailVos
import com.example.cocktailrecipe.mvp.presenters.CocatailListPresenter
import com.example.cocktailrecipe.mvp.views.CocatailListView
import com.example.cocktailrecipe.utils.ConnectionLiveData
import kotlinx.android.synthetic.main.activity_cocatail.*

class CocktailActivity : BaseActivity(), CocatailListView, SwipeRefreshLayout.OnRefreshListener {


    private lateinit var mPresenter: CocatailListPresenter
    private lateinit var cocktailAdapter: CocktailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocatail)
        mPresenter = ViewModelProviders.of(this).get(CocatailListPresenter::class.java)
        mPresenter.initPresenter(this)
        CocatailModel.getObj().loadCocktails(mPresenter.getLiveData(), mPresenter.getErrorLD())

        cocktailAdapter = CocktailAdapter(mPresenter)
        mPresenter.getLiveData().observe(this, Observer<CocatailVos> {
            displayCocatailList(it!!.drinks)

        })
        mPresenter.getErrorLD().observe(this, this)


        rvCocatail.adapter = cocktailAdapter
        rvCocatail.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        swipeRefresh.setOnRefreshListener(this)

        val connectionLiveData = ConnectionLiveData(this)
        connectionLiveData.observe(this, Observer { isConnected ->
            isConnected?.let {
                if (it) {
                    lavOffline.visibility = View.GONE
                    rlCocktail.visibility = View.VISIBLE
                    CocatailModel.getObj().loadCocktails(mPresenter.getLiveData(), mPresenter.getErrorLD())
                } else {
                    lavOffline.visibility = View.VISIBLE
                    rlCocktail.visibility = View.GONE

                }
            }
        })


    }

    override fun displayCocatailList(cocatailList: List<CocatailVo>) {

        if (pBar.isShown && pBar != null) {
            pBar.visibility = View.GONE
        }
        if (swipeRefresh.isRefreshing) {

            swipeRefresh.isRefreshing = false
            cocktailAdapter.appendCoctailList(cocatailList)

        } else {
            cocktailAdapter.setCocatailVoList(cocatailList as MutableList<CocatailVo>)
        }


    }

    override fun displaycocktailDetails(cocatailVo: CocatailVo) {
        startActivity(CocktailDetailsActivity.newIntent(this, cocatailVo.idDrink))

    }

    override fun onRefresh() {

        cocktailAdapter.clearCocatailList()
        CocatailModel.getObj().loadCocktails(mPresenter.getLiveData(), mPresenter.getErrorLD())
    }

    override fun displayErrorMsg(errorMsg: String) {
        pBar.visibility = View.GONE
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false
        }
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
    }
}
