package com.example.cocktailrecipe.mvp.views

import com.example.cocktailrecipe.data.vo.CocatailVo

interface CocatailListView {
    fun displayCocatailList(cocatailList: List<CocatailVo>)
    fun displaycocktailDetails(cocatailVo: CocatailVo)
}