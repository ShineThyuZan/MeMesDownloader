package com.example.cocktailrecipe.data.vo

import com.google.gson.annotations.SerializedName

class CocatailVo(
    @SerializedName("strDrink")
    var strDrink: String = "",
    @SerializedName("strDrinkThumb")
    var strDrinkThumb: String = "",
    @SerializedName("idDrink")
    var idDrink: String = "",
    @SerializedName("strInstructions")
    var strInstructions: String = "",
    @SerializedName("strIngredient1")
    var strIngredient1: String = "",
    @SerializedName("strIngredient2")
    var strIngredient2: String = "",
    @SerializedName("strIngredient3")
    var strIngredient3: String = "",
    @SerializedName("strIngredient4")
    var strIngredient4: String = "",
    @SerializedName("strMeasure1")
    var strMeasure1: String = "",
    @SerializedName("strMeasure2")
    var strMeasure2: String = "",
    @SerializedName("strMeasure3")
    var strMeasure3: String = "",
    @SerializedName("strCreativeCommonsConfirmed")
    var strCreativeCommonsConfirmed: String = ""


)