package com.tpo.cocktailrecipe.delegate

import com.tpo.cocktailrecipe.data.vo.MeMePagerData

interface MeMePagerItemClickDelegate {

    fun onItemClicked(data : MeMePagerData)
    fun loadMore()
}