package com.example.instantsystemtechnicaltest.data.repositories

import com.example.instantsystemtechnicaltest.data.model.Article
import com.example.instantsystemtechnicaltest.utils.Resource

interface NewsRepository {
    suspend fun getNewsData(country: String) : Resource<List<Article>?>
}