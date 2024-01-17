/*
 * This file is part of the NewsApp application.
 * It defines the use case related to news operations in the NewsApp domain.
 */
package com.example.newsapp.domain.usecases.news

import androidx.paging.PagingData
import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case responsible for retrieving a flow of paged news articles.
 *
 * This use case delegates the operation to a [NewsRepository] to fetch news articles based on the provided sources.
 *
 * @property newsRepository The repository responsible for managing news-related data.
 */
class GetNews(
    private val newsRepository: NewsRepository
) {

    /**
     * Invokes the use case, initiating the process of retrieving a flow of paged news articles.
     *
     * @param sources List of source identifiers to filter the news articles.
     * @return A [Flow] emitting [PagingData] of [Article] objects.
     */
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }

}