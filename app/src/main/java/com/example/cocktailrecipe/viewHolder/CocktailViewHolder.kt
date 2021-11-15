package com.example.cocktailrecipe.viewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.cocktailrecipe.R
import com.example.cocktailrecipe.data.vo.CocatailVo
import com.example.cocktailrecipe.delegate.CocatailDelegate
import kotlinx.android.synthetic.main.viewholder_cocktail.view.*

class CocktailViewHolder(var itemViews: View, var cocatailDelegate: CocatailDelegate) : RecyclerView.ViewHolder(itemViews) {

    private lateinit var cocatailVo: CocatailVo

    init {
        itemViews.setOnClickListener {
            cocatailDelegate.onTapCocatail(cocatailVo)

        }
    }

    fun setCocktailData(cocatailVo: CocatailVo) {
        this.cocatailVo = cocatailVo

        itemViews.tv_bottom.text = cocatailVo.strDrink
        Glide.with(itemViews.iv_show!!.context)
            .load(cocatailVo.strDrinkThumb)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(itemViews.iv_show!!)


    }
}