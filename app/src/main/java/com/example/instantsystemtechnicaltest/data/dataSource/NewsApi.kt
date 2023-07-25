package com.example.instantsystemtechnicaltest.data.dataSource

import com.example.instantsystemtechnicaltest.Constants
import com.example.instantsystemtechnicaltest.data.model.NewsResult
import com.example.instantsystemtechnicaltest.utils.Resource
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    


    @GET("top-headline")
    suspend fun getNews(
        @Query("country") country : String,
        @Field("apiKey") apiKey : String = Constants.apiKey
    ) : Resource<NewsResult>


}