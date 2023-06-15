package com.tpo.memes.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.tpo.memes.R
import com.tpo.memes.data.vo.MeMePagerData
import com.tpo.memes.delegate.MeMePagerItemClickDelegate
import com.tpo.memes.viewHolder.BaseViewHolder
import com.tpo.memes.viewHolder.MeMePagerViewHolder


class MeMePhotoPagerAdapter (context: Context, val delegate: MeMePagerItemClickDelegate) :
    BaseRecyclerAdapter<MeMePagerViewHolder, MeMePagerData>(context) {
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1
    private var lastPage = false
    private var isLoading = false
    var proBar: ProgressBar? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MeMePagerData> {


        return if (viewType == VIEW_TYPE_ITEM) {
            val v = mLayoutInflator.inflate(R.layout.single_product_viewpod, parent, false)
            MeMePagerViewHolder(v, delegate)
        } else {
            val view = mLayoutInflator.inflate(R.layout.view_holder_loadmore, parent, false)
            LoadMoreViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<MeMePagerData>, position: Int) {
        if (holder is MeMePagerViewHolder) {
            super.onBindViewHolder(holder, position)
        } else {

            if (lastPage || position == 0) {

                proBar = holder.itemView.findViewById<View>(R.id.progressBar) as ProgressBar
                proBar!!.visibility = View.GONE
            } else {
                proBar!!.visibility = View.VISIBLE

            }
        }
        if (position == mData!!.size - 10 && !lastPage && !isLoading) {
            delegate.loadMore()
        }
    }

    fun isLastPage(lastPage: Boolean) {
        this.lastPage = lastPage
        notifyDataSetChanged()
    }

    fun isLoading(isLoading: Boolean) {
        this.isLoading = isLoading
    }

    override fun getItemViewType(position: Int): Int {
        return if (mData!!.size == position) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    override fun getItemCount(): Int {
        return if (mData == null && mData!!.size == 0) 0 else mData!!.size + 1
    }

    class LoadMoreViewHolder(v: View) : BaseViewHolder<MeMePagerData>(v) {
        override fun setData(data: MeMePagerData) {
        }

        override fun onClick(v: View?) {
        }
    }
}