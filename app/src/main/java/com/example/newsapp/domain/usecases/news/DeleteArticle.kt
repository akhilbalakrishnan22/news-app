package com.example.newsapp.domain.usecases.news

import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.repository.NewsRepository

class DeleteArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article) {
        newsRepository.deleteArticle(article = article)
    }
}