package com.tpo.cocktailrecipe.mvp.views

import com.tpo.cocktailrecipe.data.vo.CocatailVo

interface CocatailListView {
    fun displayCocatailList(cocatailList: List<CocatailVo>)
    fun displaycocktailDetails(cocatailVo: CocatailVo)
}