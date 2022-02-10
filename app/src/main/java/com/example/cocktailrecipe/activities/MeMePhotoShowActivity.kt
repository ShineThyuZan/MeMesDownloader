package com.example.cocktailrecipe.activities

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailrecipe.R
import com.example.cocktailrecipe.adapters.MemePhotoListAdapter
import com.example.cocktailrecipe.data.model.CocktailViewModel
import com.example.cocktailrecipe.data.vo.MemeData
import com.example.cocktailrecipe.delegate.MeMePhotoItemDelegate


class MeMePhotoShowActivity : BaseActivity(), MeMePhotoItemDelegate {
    private lateinit var memePhotoListAdapter: MemePhotoListAdapter
    private lateinit var mViewModel: CocktailViewModel
    private lateinit var mMemeListData: MutableList<MemeData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activit_meme_list)

        // Model create to use this Activity
        mViewModel = ViewModelProviders.of(this@MeMePhotoShowActivity)
            .get(CocktailViewModel::class.java)

        // create adapter
        memePhotoListAdapter = MemePhotoListAdapter(baseContext, this)

        // Api Call
        mViewModel.getMeMeListPhoto()

        //Api Response catch
        observe()

    }

    private fun observe() {
        mViewModel.memeListResponse.observe(this, Observer {
            memePhotoListAdapter.setNewDataList(it.data.memes as MutableList<MemeData>)
            this.findViewById<RecyclerView>(R.id.rvMeMeList).apply {
                adapter = memePhotoListAdapter
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
            }
        })
        mViewModel.mErrorLD.observe(
            this, Observer {
                Toast.makeText(this, "In error ", Toast.LENGTH_SHORT).show()
            }
        )

    }

    override fun onMeMeItemClicked(memeVos: MemeData) {
        startActivity(FullScreenImageActivity.newIntent(this, memeVos.url))
    }
}