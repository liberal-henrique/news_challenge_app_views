package com.example.newsapp.data


import com.example.newsapp.models.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MyApi {

    @GET("v2/top-headlines")
    fun getTopHeadlines(
        @Query("country")
        country: String = "us",
        @Query("category")
        category: String = NewsCategory.Business.param
        @Query("apiKey") apiKey: String
    ): Call<SourcesResponse>
}