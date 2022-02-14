package com.tpo.cocktailrecipe.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.tpo.cocktailrecipe.R
import com.tpo.cocktailrecipe.activities.FullScreenImageActivity
import com.tpo.cocktailrecipe.adapters.MeMePhotoPagerAdapter
import com.tpo.cocktailrecipe.data.model.CocktailViewModel
import com.tpo.cocktailrecipe.data.vo.MeMePagerData
import com.tpo.cocktailrecipe.delegate.MeMePagerItemClickDelegate

class MeMePhotoPagerFragment : BaseFragment() {
    private lateinit var mView: View
    private lateinit var memePhotoPagerAdapter: MeMePhotoPagerAdapter
    private lateinit var mViewModel: CocktailViewModel
    private var mAdView: AdView? = null
    private lateinit var memeDataList: MutableList<MeMePagerData>
    private var pageNumber = 1
    private var firstTime = true
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mView = inflater.inflate(R.layout.fragment_meme_pager, container, false)
        memeDataList = mutableListOf()
        MobileAds.initialize(requireContext()) {
            mAdView = mView.findViewById(R.id.adView)
            val adRequest = AdRequest.Builder().build()
            mAdView!!.loadAd(adRequest)
        }

        mViewModel = ViewModelProviders.of(requireActivity())[CocktailViewModel::class.java]

        mViewModel.getMeMePhotoPager(pageNumber++)
        memePhotoPagerAdapter =
            MeMePhotoPagerAdapter(requireContext(), object : MeMePagerItemClickDelegate {
                override fun onItemClicked(data: MeMePagerData) {
                    startActivity(
                        FullScreenImageActivity.newIntent(
                            requireContext(),
                            data.image,
                            data.name,
                            data.rank.toString()
                        )
                    )
                }

                override fun loadMore() {
                    mViewModel.getMeMePhotoPager(pageNumber++)
                    isLoading = true
                    memePhotoPagerAdapter.isLoading(isLoading)
                }
            })

        observe()

        mView.findViewById<RecyclerView>(R.id.rvMeMePagerList).apply {
            adapter = memePhotoPagerAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
        return mView
    }

    private fun observe() {
        mViewModel.memePagerListResponse.observe(requireActivity(), Observer {

            if (it.code == "200") {
                mView.findViewById<ImageView>(R.id.ivOffline).visibility = View.GONE
                isLoading = false
                memePhotoPagerAdapter.isLoading(isLoading)

                if (it.data.isEmpty()) {
                    memePhotoPagerAdapter.isLastPage(true)
                    if (firstTime) {
                        isLoading = true
                        memePhotoPagerAdapter.isLoading(isLoading)
                    }
                } else {
                    firstTime = false
                    if (it.data.size < 10) {
                        memePhotoPagerAdapter.isLastPage(true)
                    } else {
                        memePhotoPagerAdapter.isLastPage(false)
                    }
                    memePhotoPagerAdapter.appendNewData(it.data)
                }

//                memeDataList = it.data as MutableList<MeMePagerData>
//                memePhotoPagerAdapter.setNewDataList(it.data as MutableList<MeMePagerData>)
            } else {
                mView.findViewById<ImageView>(R.id.ivOffline).visibility = View.VISIBLE
            }
        })
        mViewModel.mErrorLD.observe(
            requireActivity(), Observer {
//                Toast.makeText(requireContext(), "In error ", Toast.LENGTH_SHORT).show()
            }
        )
    }

}