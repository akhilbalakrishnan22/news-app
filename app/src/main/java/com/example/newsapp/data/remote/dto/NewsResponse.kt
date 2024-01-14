package com.example.newsapp.data.remote.dto

import com.example.newsapp.domain.models.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)