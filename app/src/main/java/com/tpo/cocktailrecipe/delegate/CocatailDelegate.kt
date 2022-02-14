package com.tpo.cocktailrecipe.delegate

import com.tpo.cocktailrecipe.data.vo.CocatailVo

interface CocatailDelegate {
    fun onTapCocatail(cocatailVo: CocatailVo)
}