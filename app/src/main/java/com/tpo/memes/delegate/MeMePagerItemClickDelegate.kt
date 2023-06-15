package com.tpo.memes.delegate

import com.tpo.memes.data.vo.MeMePagerData

interface MeMePagerItemClickDelegate {

    fun onItemClicked(data : MeMePagerData)
    fun loadMore()
}