package com.example.cocktailrecipe.adapters

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailrecipe.data.vo.IngredientVo
import com.example.cocktailrecipe.viewHolder.IngredientViewHolder
import java.util.*


class IngredientAdapter() : RecyclerView.Adapter<IngredientViewHolder>(), Parcelable {


    private var ingredientList: MutableList<IngredientVo>? = null

    constructor(parcel: Parcel) : this() {

    }


    init {
        ingredientList = ArrayList()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(com.example.cocktailrecipe.R.layout.viewholder_ingredient, parent, false)

        return IngredientViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.setCocktailData(ingredientList!![position])
    }

    override fun getItemCount(): Int {

        return ingredientList!!.size
    }

    fun setIngredientList(ingredientVo: MutableList<IngredientVo>) {


        for (i in 0 until ingredientVo.size) {
            if (ingredientVo[i].ingredient.isEmpty() || ingredientVo[i].ingredient == "") {
                ingredientVo.removeAt(i)
            }
        }
        this.ingredientList = ingredientVo
        notifyDataSetChanged()
    }

    fun appendIngredientList(ingredientList: List<IngredientVo>) {
        this.ingredientList!!.addAll(ingredientList)
        notifyDataSetChanged()
    }

    fun clearIngredientList() {
        this.ingredientList = ArrayList()
        notifyDataSetChanged()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IngredientAdapter> {
        override fun createFromParcel(parcel: Parcel): IngredientAdapter {
            return IngredientAdapter(parcel)
        }

        override fun newArray(size: Int): Array<IngredientAdapter?> {
            return arrayOfNulls(size)
        }
    }

}