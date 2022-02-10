package com.example.cocktailrecipe.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.cocktailrecipe.R
import com.example.cocktailrecipe.data.vo.CocatailVo
import com.example.cocktailrecipe.delegate.CocatailDelegate

class CocktailViewHolder(
    itemView: View,
    private val delegate: CocatailDelegate
) :
    BaseViewHolder<CocatailVo>(itemView) {

    override fun setData(data: CocatailVo) {
        val tvBottom = itemView.findViewById<TextView>(R.id.tv_bottom)
        val imgShow = itemView.findViewById<ImageView>(R.id.iv_show)
        mData = data
        tvBottom.text = mData.strDrink

        Glide.with(itemView.context)
            .load(mData.strDrinkThumb)
            .placeholder(R.drawable.placeholder)
            .into(imgShow)
    }

    override fun onClick(v: View?) {
        delegate.onTapCocatail(mData)
    }

}

//class CocktailViewHolder(var itemViews: View, var cocatailDelegate: CocatailDelegate) :
//    BaseViewHolder<CocatailVo>(itemViews) {
//
//    private lateinit var cocatailVo: CocatailVo
//
//
//    fun setCocktailData(cocatailVo: CocatailVo) {
//        this.cocatailVo = cocatailVo
//
//        itemViews.tv_bottom.text = cocatailVo.strDrink
//        Glide.with(itemViews.iv_show!!.context)
//            .load(cocatailVo.strDrinkThumb)
//            .placeholder(R.drawable.placeholder)
//            .error(R.drawable.placeholder)
//            .into(itemViews.iv_show!!)
//
//
//    }
//}