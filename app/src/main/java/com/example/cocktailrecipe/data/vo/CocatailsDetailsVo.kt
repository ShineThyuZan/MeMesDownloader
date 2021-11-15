package com.example.cocktailrecipe.data.vo

import com.google.gson.annotations.SerializedName

class CocatailsDetailsVo(
    @SerializedName("strInstructions") var strInstructions: String = "",
    @SerializedName("strDrinkThumb") var strDrinkThumb: String = ""
)