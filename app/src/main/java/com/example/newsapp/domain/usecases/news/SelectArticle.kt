/*
 * This file is part of the NewsApp application.
 * It defines the use case related to news operations in the NewsApp domain.
 */
package com.example.newsapp.domain.usecases.news

import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.repository.NewsRepository

/**
 * Use case responsible for selecting a single news article by its URL.
 *
 * This use case delegates the operation to a [NewsRepository] to retrieve a specific article.
 *
 * @property newsRepository The repository responsible for managing news-related data.
 */
class SelectArticle(
    private val newsRepository: NewsRepository
) {

    /**
     * Invokes the use case, initiating the process of selecting a single news article by its URL.
     *
     * @param url The URL of the article to be selected.
     * @return The [Article] object corresponding to the provided URL, or null if not found.
     */
    suspend operator fun invoke(url: String): Article? {
        return newsRepository.selectArticle(url)
    }
}