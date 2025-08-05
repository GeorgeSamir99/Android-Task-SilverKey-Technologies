package com.example.androidtasksilverkeytechnologies.news_api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ApiManger {
    companion object{
        private var retrofit : Retrofit? = null
        private const val BASE_URL = "https://newsapi.org/"
        val source = "the-next-web"
        const val API_KEY = "9a4d22cf8e74433fa10aca7c41e42bc2"
        private fun getRetrofitInstance():Retrofit{
            if (retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit !!
        }
        fun getNewsServices() :NewsServices{
            return getRetrofitInstance().create(NewsServices::class.java)
        }
    }
}