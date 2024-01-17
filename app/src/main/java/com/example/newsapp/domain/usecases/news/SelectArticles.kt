/*
 * This file is part of the NewsApp application.
 * It defines the use case related to news operations in the NewsApp domain.
 */
package com.example.newsapp.domain.usecases.news

import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case responsible for selecting a list of news articles.
 *
 * This use case delegates the operation to a [NewsRepository] to retrieve a flow of articles.
 *
 * @property newsRepository The repository responsible for managing news-related data.
 */
class SelectArticles(
    private val newsRepository: NewsRepository
) {

    /**
     * Invokes the use case, initiating the process of selecting a list of news articles.
     *
     * @return A [Flow] emitting a list of [Article] objects.
     */
    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }
}