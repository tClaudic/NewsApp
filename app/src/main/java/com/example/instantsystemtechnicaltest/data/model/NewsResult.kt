package com.example.instantsystemtechnicaltest.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsResult(
    @Json(name = "articles")
    val articles: List<Article?>? = null,
    @Json(name = "status")
    val status: String? = null,
    @Json(name = "totalResults")
    val totalResults: Int? = null
)