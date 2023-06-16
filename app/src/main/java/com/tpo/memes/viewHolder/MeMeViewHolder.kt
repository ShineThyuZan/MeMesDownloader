package com.tpo.memes.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.tpo.memes.R
import com.tpo.memes.data.vo.MemeData
import com.tpo.memes.delegate.MeMePhotoItemDelegate

class MeMeViewHolder(
    itemView: View,
    private val delegate: MeMePhotoItemDelegate
) :
    BaseViewHolder<MemeData>(itemView) {

    override fun setData(data: MemeData) {
        val tvItemName = itemView.findViewById<TextView>(R.id.tvItemName)
        val tvWidth = itemView.findViewById<TextView>(R.id.tvWidth)
        val tvHeight = itemView.findViewById<TextView>(R.id.tvHeight)
        val imgMeme = itemView.findViewById<ImageView>(R.id.ivMeMe)

        mData = data

        tvItemName.text = mData.name
        tvWidth.text = mData.width.toString()
        tvHeight.text = "${mData.height} x"


        Glide.with(itemView.context)
            .load(mData.url)
            .placeholder(R.drawable.placeholder)
            .into(imgMeme)
    }

    override fun onClick(v: View?) {
        delegate.onMeMeItemClicked(mData)
    }

}