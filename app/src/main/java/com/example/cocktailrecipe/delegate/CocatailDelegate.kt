package com.example.cocktailrecipe.delegate

import com.example.cocktailrecipe.data.vo.CocatailVo

interface CocatailDelegate {
    fun onTapCocatail(cocatailVo: CocatailVo)
}