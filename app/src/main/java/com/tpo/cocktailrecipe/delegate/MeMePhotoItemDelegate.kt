package com.tpo.cocktailrecipe.delegate

import com.tpo.cocktailrecipe.data.vo.MemeData

interface MeMePhotoItemDelegate {
    fun onMeMeItemClicked(memeVos: MemeData)
}