package com.example.newsapp.domain.usecases.news

import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.repository.NewsRepository

class SelectArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String): Article? {
        return newsRepository.selectArticle(url)
    }
}