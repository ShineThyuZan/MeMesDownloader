package com.example.cocktailrecipe.network

import com.example.cocktailrecipe.data.vo.CocktailResponse
import com.example.cocktailrecipe.data.vo.MeMeResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface BaseAPI {

    @GET("filter.php?a=Non_Alcoholic")
    fun loadCocktailList(): Observable<CocktailResponse>

    //https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=12692
    @GET("lookup.php")
    fun loadCocktailDetails(@Query("i")  params: String): Observable<CocktailResponse>
   // fun loadCocktailDetails(@QueryMap params: Map<String, String>): Observable<CocatailVos>

   @GET("https://api.imgflip.com/get_memes")
   fun getMeMePhotoList(): Observable<MeMeResponse>

}