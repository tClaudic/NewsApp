package com.example.instantsystemtechnicaltest.data.dataSource

import com.example.instantsystemtechnicaltest.Constants
import com.example.instantsystemtechnicaltest.data.model.NewsResult
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    


    @GET("top-headlines")
    suspend fun getNews(
        @Query("language") language : String,
        @Query("apiKey") apiKey : String = Constants.apiKey
    ) : NewsResult


}