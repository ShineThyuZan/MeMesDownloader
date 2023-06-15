package com.tpo.cocktailrecipe.network

import com.tpo.cocktailrecipe.data.vo.MeMePagerResponse
import com.tpo.cocktailrecipe.data.vo.MeMeResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface BaseAPI {

    @GET("https://api.imgflip.com/get_memes")
    fun getMeMePhotoList(): Observable<MeMeResponse>

    @GET("https://meme-api.com/gimme/wholesomememes/{count}")
    fun getMeMePhotoPager(
        @Path("count") count: Int
    ): Observable<MeMePagerResponse>

}