/*
 * This file is part of the NewsApp application.
 * It defines the use case related to searching news articles in the NewsApp domain.
 */
package com.example.newsapp.domain.usecases.news

import androidx.paging.PagingData
import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case responsible for searching news articles based on a query.
 *
 * This use case delegates the operation to a [NewsRepository] to fetch paged news articles.
 *
 * @property newsRepository The repository responsible for managing news-related data.
 */
class SearchNews(
    private val newsRepository: NewsRepository
) {

    /**
     * Invokes the use case, initiating the process of searching news articles based on a query.
     *
     * @param searchQuery The search query string.
     * @param sources List of source identifiers to filter the news articles.
     * @return A [Flow] emitting [PagingData] of [Article] objects.
     */
    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNews(searchQuery = searchQuery, sources = sources)
    }

}