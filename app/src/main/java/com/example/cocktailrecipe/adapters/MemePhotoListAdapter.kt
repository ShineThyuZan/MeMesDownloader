package com.example.cocktailrecipe.adapters

import android.content.Context
import android.view.ViewGroup
import com.example.cocktailrecipe.R
import com.example.cocktailrecipe.data.vo.MemeData
import com.example.cocktailrecipe.delegate.MeMePhotoItemDelegate
import com.example.cocktailrecipe.viewHolder.BaseViewHolder
import com.example.cocktailrecipe.viewHolder.MeMeViewHolder

class MemePhotoListAdapter(context: Context, val delegate: MeMePhotoItemDelegate) :
    BaseRecyclerAdapter<MeMeViewHolder, MemeData>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MemeData> {
        val view = mLayoutInflator.inflate(R.layout.meme_item_view_pod, parent, false)
        return MeMeViewHolder(view, delegate)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<MemeData>, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.setOnClickListener {
            delegate.onMeMeItemClicked(mData!![position])
        }
    }

}
