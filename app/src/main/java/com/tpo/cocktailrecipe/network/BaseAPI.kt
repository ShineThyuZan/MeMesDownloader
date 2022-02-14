package com.tpo.cocktailrecipe.network

import com.tpo.cocktailrecipe.data.vo.CocktailResponse
import com.tpo.cocktailrecipe.data.vo.MeMePagerResponse
import com.tpo.cocktailrecipe.data.vo.MeMeResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
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

   @GET("http://alpha-meme-maker.herokuapp.com/{pageNo}")
   fun getMeMePhotoPager(
       @Path("pageNo") userId: Int
   ) : Observable<MeMePagerResponse>

}