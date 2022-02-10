package com.example.cocktailrecipe.delegate

import com.example.cocktailrecipe.data.vo.CocatailVo
import com.example.cocktailrecipe.data.vo.MemeData

interface MeMePhotoItemDelegate {
    fun onMeMeItemClicked(memeVos: MemeData)
}