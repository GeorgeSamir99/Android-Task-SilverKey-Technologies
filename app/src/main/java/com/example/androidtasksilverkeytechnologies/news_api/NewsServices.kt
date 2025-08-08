package com.example.androidtasksilverkeytechnologies.news_api

import com.example.androidtasksilverkeytechnologies.news_api.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {

    @GET("v2/top-headlines")
    suspend fun getTopHeaLines(
        @Query("sources") source: String = ApiManger.source,
        @Query("apiKey") apiKey: String = ApiManger.API_KEY
    ):NewsResponse
}