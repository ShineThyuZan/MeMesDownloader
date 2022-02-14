package com.tpo.cocktailrecipe.mvp.views

import com.tpo.cocktailrecipe.data.vo.CocatailVo

interface CocktailDetailView {
    fun displayCotailDetail(cocatailVo: CocatailVo)
}