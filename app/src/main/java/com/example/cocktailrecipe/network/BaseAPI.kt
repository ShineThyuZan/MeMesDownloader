package com.example.cocktailrecipe.network

import com.example.cocktailrecipe.data.vo.CocatailVos
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface BaseAPI {


    @GET("filter.php?a=Non_Alcoholic")
    fun loadCocktailList(): Observable<CocatailVos>

    //https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=12692
    @GET("lookup.php")
    fun loadCocktailDetails(@Query("i")  params: String): Observable<CocatailVos>
   // fun loadCocktailDetails(@QueryMap params: Map<String, String>): Observable<CocatailVos>
}