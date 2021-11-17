package com.example.cocktailrecipe.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.cocktailrecipe.R
import com.example.cocktailrecipe.adapters.IngredientAdapter
import com.example.cocktailrecipe.data.model.CocktailViewModel
import com.example.cocktailrecipe.data.vo.CocatailVo
import com.example.cocktailrecipe.data.vo.IngredientVo
import com.example.cocktailrecipe.mvp.views.CocktailDetailView
import kotlinx.android.synthetic.main.activity_cocatail.*
import kotlinx.android.synthetic.main.activity_cocktail_detail.*


class CocktailDetailsActivity : BaseActivity(), CocktailDetailView {
    //private lateinit var mPresenter: CocktailDetailPresenter
    private lateinit var cocktailId: String
    private lateinit var intgredientList: MutableList<IngredientVo>
    private lateinit var cocktailDetailData: MutableList<CocatailVo>
    private lateinit var ingredientAdapter: IngredientAdapter
    private lateinit var cocktailModel: CocktailViewModel

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

        // take cocktail id
        if (intent.hasExtra(IE_COCKTAIL_ID)) {
            cocktailId = intent.getStringExtra(IE_COCKTAIL_ID)
        }

        // view model provide for this activity
        cocktailModel = ViewModelProviders.of(this@CocktailDetailsActivity)
            .get(CocktailViewModel::class.java)

        // api call
        cocktailModel.getCocktailListDetail(cocktailId)

        observe()

    }

    private fun observe() {
        cocktailModel.cocktailListDetailResponse.observe(this, Observer {


            cocktailDetailData = it?.drinks as MutableList<CocatailVo>
            Glide.with(img)
            .load(cocktailDetailData[0].strDrinkThumb)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(img)

        })
        // show error
        cocktailModel.mErrorLD.observe(
            this, Observer {
                showPromptDialog(it)
            }
        )
    }

    override fun displayCotailDetail(cocatailVo: CocatailVo) {
        TODO("Not yet implemented")
    }


}


//    override fun displayCotailDetail(cocatailVo: CocatailVo) {
//        Glide.with(ivCocktailDetail)
//            .load(cocatailVo.strDrinkThumb)
//            .placeholder(R.drawable.placeholder)
//            .error(R.drawable.placeholder)
//            .into(ivCocktailDetail)
//
//        intgredientList = ArrayList()
//        intgredientList.add(IngredientVo(cocatailVo.strIngredient1, cocatailVo.strMeasure1))
//        intgredientList.add(IngredientVo(cocatailVo.strIngredient2, cocatailVo.strMeasure2))
//        intgredientList.add(IngredientVo(cocatailVo.strIngredient3, cocatailVo.strMeasure3))
//        ingredientAdapter = IngredientAdapter()
//        ingredientAdapter.setIngredientList(intgredientList)
//        rvIngredient.adapter = ingredientAdapter
//        rvIngredient.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
//        tvDescription.text = cocatailVo.strInstructions
//        tvToolbar.text = cocatailVo.strDrink
//        tvToolbar.setTypeface(null, Typeface.BOLD)
//    }




