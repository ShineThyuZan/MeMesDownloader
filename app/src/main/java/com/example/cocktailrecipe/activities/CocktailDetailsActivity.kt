package com.example.cocktailrecipe.activities

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.example.cocktailrecipe.R
import com.example.cocktailrecipe.adapters.IngredientAdapter
import com.example.cocktailrecipe.data.vo.CocatailVo
import com.example.cocktailrecipe.data.vo.IngredientVo
import com.example.cocktailrecipe.mvp.views.CocktailDetailView
import kotlinx.android.synthetic.main.activity_cocktail_detail.*


class CocktailDetailsActivity : BaseActivity(), CocktailDetailView {
    //private lateinit var mPresenter: CocktailDetailPresenter
    private lateinit var cocktailId: String
    private lateinit var intgredientList: MutableList<IngredientVo>
    private lateinit var ingredientAdapter: IngredientAdapter

    companion object {

        private const val IE_COCKTAIL_ID = "IE_COCKTAIL_ID"
        fun newIntent(context: Context, cocktailId: String): Intent {
            val intent = Intent(context, CocktailDetailsActivity::class.java)
            intent.putExtra(IE_COCKTAIL_ID, cocktailId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocktail_detail)
//        mPresenter = ViewModelProviders.of(this).get(CocktailDetailPresenter::class.java)
//        mPresenter.initPresenter(this)
//        cocktailId = intent.getStringExtra(IE_COCKTAIL_ID)
//        mPresenter.onUiReady(cocktailId)
//
//        mPresenter.getLiveData().observe(this, Observer<CocatailVos> {
//            displayCotailDetail(it!!.drinks[0])
//
//        })
//        mPresenter.getErrorLD().observe(this, this)
//        ivBack.setOnClickListener {
//            onBackPressed()
//        }
//
//        val connectionLiveData = ConnectionLiveData(this)
//        connectionLiveData.observe(this, Observer { isConnected ->
//            isConnected?.let {
//                if (it) {
//                    lavOffline.visibility = View.GONE
//                    appbar.visibility = View.VISIBLE
//                    nestedScrollView.visibility = View.VISIBLE
//                    RetrofitProvider.getObj().loadCocktailDetail(mPresenter.getLiveData(), mPresenter.getErrorLD(), cocktailId)
//                } else {
//                    lavOffline.visibility = View.VISIBLE
//                    appbar.visibility = View.GONE
//                    nestedScrollView.visibility = View.GONE
//
//
//                }
//            }
//        })
    }

    override fun displayCotailDetail(cocatailVo: CocatailVo) {
        Glide.with(ivCocktailDetail)
            .load(cocatailVo.strDrinkThumb)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(ivCocktailDetail)

        intgredientList = ArrayList()
        intgredientList.add(IngredientVo(cocatailVo.strIngredient1, cocatailVo.strMeasure1))
        intgredientList.add(IngredientVo(cocatailVo.strIngredient2, cocatailVo.strMeasure2))
        intgredientList.add(IngredientVo(cocatailVo.strIngredient3, cocatailVo.strMeasure3))
        ingredientAdapter = IngredientAdapter()
        ingredientAdapter.setIngredientList(intgredientList)
        rvIngredient.adapter = ingredientAdapter
        rvIngredient.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        tvDescription.text = cocatailVo.strInstructions
        tvToolbar.text = cocatailVo.strDrink
        tvToolbar.setTypeface(null, Typeface.BOLD)
    }


}

