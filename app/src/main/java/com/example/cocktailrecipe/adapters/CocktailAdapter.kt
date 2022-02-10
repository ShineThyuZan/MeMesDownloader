package com.example.cocktailrecipe.adapters

import android.content.Context
import android.view.ViewGroup
import com.example.cocktailrecipe.R
import com.example.cocktailrecipe.data.vo.CocatailVo
import com.example.cocktailrecipe.delegate.CocatailDelegate
import com.example.cocktailrecipe.viewHolder.BaseViewHolder
import com.example.cocktailrecipe.viewHolder.CocktailViewHolder

class CocktailAdapter(context: Context, val delegate: CocatailDelegate) :
    BaseRecyclerAdapter<CocktailViewHolder, CocatailVo>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<CocatailVo> {
        val view = mLayoutInflator.inflate(R.layout.viewholder_cocktail, parent, false)
        return CocktailViewHolder(view, delegate)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<CocatailVo>, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.setOnClickListener {
            delegate.onTapCocatail(mData!![position])
        }
    }
}


//
//class CocktailAdapter(var cocatailDelegate: CocatailDelegate) : BaseRecyclerAdapter<CocktailViewHolder, CocatailVo>(context) {
//
//
//
//    private var cocatailVoList: MutableList<CocatailVo>? = null
//
//
//    init {
//        cocatailVoList = ArrayList()
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.viewholder_cocktail, parent, false)
//
//        return CocktailViewHolder(view, cocatailDelegate)
//    }
//
//    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
//        holder.setCocktailData(cocatailVoList!![position])
//    }
//
//    override fun getItemCount(): Int {
//
//        return cocatailVoList!!.size
//    }
//
//    fun setCocatailVoList(cocatailVoList: MutableList<CocatailVo>) {
//
//        this.cocatailVoList = cocatailVoList
//        notifyDataSetChanged()
//    }
//
//    fun appendCoctailList(cocktailList: List<CocatailVo>) {
//
//        this.cocatailVoList!!.addAll(cocktailList)
//        notifyDataSetChanged()
//    }
//
//    fun clearCocatailList() {
//        this.cocatailVoList = ArrayList()
//        notifyDataSetChanged()
//    }
//}