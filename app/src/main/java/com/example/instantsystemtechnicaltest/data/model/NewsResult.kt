package com.example.instantsystemtechnicaltest.data.model

data class NewsResult(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)