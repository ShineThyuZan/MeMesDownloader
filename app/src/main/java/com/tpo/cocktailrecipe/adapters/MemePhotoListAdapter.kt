package com.tpo.cocktailrecipe.adapters

import     android.content.Context
import android.view.ViewGroup
import com.tpo.cocktailrecipe.R
import com.tpo.cocktailrecipe.data.vo.MemeData
import com.tpo.cocktailrecipe.delegate.MeMePhotoItemDelegate
import com.tpo.cocktailrecipe.viewHolder.BaseViewHolder
import com.tpo.cocktailrecipe.viewHolder.MeMeViewHolder

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
