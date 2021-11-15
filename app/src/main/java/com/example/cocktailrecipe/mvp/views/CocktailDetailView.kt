package com.example.cocktailrecipe.mvp.views

import com.example.cocktailrecipe.data.vo.CocatailVo

interface CocktailDetailView {
    fun displayCotailDetail(cocatailVo: CocatailVo)
}