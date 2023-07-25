package com.example.instantsystemtechnicaltest.data.repositories


import android.content.res.Resources
import com.example.instantsystemtechnicaltest.R
import com.example.instantsystemtechnicaltest.data.dataSource.NewsApi
import com.example.instantsystemtechnicaltest.data.model.Article
import com.example.instantsystemtechnicaltest.utils.Resource
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {
    override suspend fun getNewsData(country: String): Resource<List<Article>?> {
        return try {
            Resource.Success(
                data = newsApi.getNews(country).data?.articles
            )
        } catch (e: Exception){
            e.printStackTrace()
            Resource.Error(
                message = e.message ?: Resources.getSystem().getString(R.string.api_response_unknown_error)
            )
        }
    }
}