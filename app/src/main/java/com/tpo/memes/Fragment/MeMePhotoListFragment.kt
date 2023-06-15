package com.tpo.memes.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.tpo.memes.R
import com.tpo.memes.activities.FullScreenImageActivity
import com.tpo.memes.adapters.MemePhotoListAdapter
import com.tpo.memes.data.model.CocktailViewModel
import com.tpo.memes.data.vo.MemeData
import com.tpo.memes.delegate.MeMePhotoItemDelegate

class MeMePhotoListFragment : BaseFragment(), MeMePhotoItemDelegate {
    private lateinit var mView: View
    private lateinit var memePhotoListAdapter: MemePhotoListAdapter
    private lateinit var mViewModel: CocktailViewModel
    private var mAdView: AdView? = null
    private lateinit var memeDataList: MutableList<MemeData>
    var isFirstTime = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_meme_photo, container, false)

        MobileAds.initialize(requireContext()) {
            mAdView = mView.findViewById(R.id.adView)
            val adRequest = AdRequest.Builder().build()
            mAdView!!.loadAd(adRequest)
        }

        mViewModel = ViewModelProviders.of(requireActivity())
            .get(CocktailViewModel::class.java)

        // create adapter
        memePhotoListAdapter = MemePhotoListAdapter(requireContext(), this)

        // Api Call
        mViewModel.getMeMeListPhoto()
        memeDataList = mutableListOf()


        observe()



        mView.findViewById<androidx.appcompat.widget.SearchView>(R.id.searchView)
            .setOnQueryTextListener(object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    val result = memeDataList.filter { data ->
                        data.name.contains(query!!)
                    }
                    memePhotoListAdapter.clearData()
                    memePhotoListAdapter.appendNewData(result as MutableList<MemeData>)

                    return true
                }

                override fun onQueryTextChange(inputSearchText: String?): Boolean {
                    if (inputSearchText.isNullOrBlank()) {
                            memePhotoListAdapter.setNewDataList(memeDataList)
                    }
                    return true
                }
            })
        mView.findViewById<RecyclerView>(R.id.rvMeMeList).apply {
            adapter = memePhotoListAdapter
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
        }
        return mView
    }

    private fun observe() {
        mViewModel.memeListResponse.observe(requireActivity(), Observer {

            if (it.success) {
                mView.findViewById<ImageView>(R.id.ivOffline).visibility = View.GONE
                memeDataList = it.data.memes as MutableList<MemeData>
                memePhotoListAdapter.setNewDataList(it.data.memes as MutableList<MemeData>)
            } else {
                mView.findViewById<ImageView>(R.id.ivOffline).visibility = View.VISIBLE
            }
        })
        mViewModel.mErrorLD.observe(
            requireActivity(), Observer {
              //  Toast.makeText(requireContext(), "In error ", Toast.LENGTH_SHORT).show()
            }
        )
    }

    override fun onMeMeItemClicked(memeVos: MemeData) {
        startActivity(FullScreenImageActivity.newIntent(requireContext(), memeVos.url, memeVos.name, memeVos.box_count.toString()))
    }


}