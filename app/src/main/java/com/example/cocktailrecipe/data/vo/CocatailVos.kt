package com.example.cocktailrecipe.data.vo

import com.google.gson.annotations.SerializedName

class CocatailVos(@SerializedName("drinks") var drinks: List<CocatailVo>)