package com.dodolife.rapidnews.network

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {

    @GET("v2/everything")
    suspend fun getNewsEverything(
        @Query("q") query : String,
        @Query("from") from: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ) : NewsResponse
}