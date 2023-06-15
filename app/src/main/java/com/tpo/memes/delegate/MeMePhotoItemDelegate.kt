package com.tpo.memes.delegate

import com.tpo.memes.data.vo.MemeData

interface MeMePhotoItemDelegate {
    fun onMeMeItemClicked(memeVos: MemeData)
}