package com.tpo.memes.viewHolder

import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.tpo.memes.R
import com.tpo.memes.data.vo.MeMePagerData
import com.tpo.memes.delegate.MeMePagerItemClickDelegate

class MeMePagerViewHolder(itemView: View, private val delegate: MeMePagerItemClickDelegate) :
    BaseViewHolder<MeMePagerData>(itemView) {
    var imageView: ShapeableImageView? = null
    var itemName: TextView? = null
    var topTextName: TextView? = null
    var ratingText: TextView? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setData(data: MeMePagerData) {
        imageView = itemView.findViewById<View>(R.id.ivProduct) as ShapeableImageView
        itemName = itemView.findViewById<View>(R.id.tvCartProductName) as TextView
        topTextName = itemView.findViewById<View>(R.id.tvTopText) as TextView
        ratingText = itemView.findViewById<View>(R.id.tvRating) as TextView
        mData = data
        Glide.with(itemView.context)
            .load(mData.url)
            .into(imageView!!)
        itemName!!.text = mData.title
        topTextName!!.text = mData.subreddit
        ratingText!!.text = mData.ups.toString()
    }

    override fun onClick(v: View?) {
        delegate.onItemClicked(mData)
    }
}