package com.example.cocktailrecipe.activities


import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailrecipe.R
import com.example.cocktailrecipe.adapters.CocktailAdapter
import com.example.cocktailrecipe.data.model.CocktailViewModel
import com.example.cocktailrecipe.data.vo.CocatailVo
import com.example.cocktailrecipe.delegate.CocatailDelegate


class CocktailActivity : BaseActivity(){

//    private lateinit var cocktailAdapter: CocktailAdapter
//    private lateinit var mViewCocktailViewModel: CocktailViewModel
//    private lateinit var mCocktailListData: MutableList<CocatailVo>
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_cocatail)
//
//        // Model create to use this Activity
//        mViewCocktailViewModel = ViewModelProviders.of(this@CocktailActivity)
//            .get(CocktailViewModel::class.java)
//
//        // create adapter
//        cocktailAdapter = CocktailAdapter(baseContext, this)
//
//        // Api Call
//        mViewCocktailViewModel.getCocktailList()
//
//        //Api Response catch
//        observe()
//
//    }
//
//
//    override fun onTapCocatail(cocatailVo: CocatailVo) {
//        Toast.makeText(this, cocatailVo.idDrink, Toast.LENGTH_LONG).show()
//        startActivity(CocktailDetailsActivity.newIntent(this, cocatailVo.idDrink))
//
//
//    }
//
//    private fun observe() {
//        mViewCocktailViewModel.catailListResponse.observe(this, Observer {
//
//            this.findViewById<ProgressBar>(R.id.pBar).visibility = View.GONE
//            // set cocktail list data to Adapter
//            mCocktailListData = it?.drinks as MutableList<CocatailVo>
//            cocktailAdapter.setNewDataList(mCocktailListData)
//
//            this.findViewById<RecyclerView>(R.id.rvCocatail).apply {
//                layoutManager = GridLayoutManager(context, 2)
//                setHasFixedSize(true)
//            }.adapter = cocktailAdapter
//
//        })
//        // show error
//        mViewCocktailViewModel.mErrorLD.observe(
//            this, Observer {
//                showPromptDialog(it)
//            }
//        )
//
//    }
}
