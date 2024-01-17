/*
 * This file is part of the NewsApp application.
 * It defines the repository to handle operations in NewsApp data layer.
 */
package com.example.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.remote.NewsPagingSource
import com.example.newsapp.data.remote.SearchNewsPagingSource
import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

/**
 * Implementation of the [NewsRepository] interface, responsible for managing
 * the retrieval and manipulation of news articles.
 */
class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) : NewsRepository {

    /**
     * Fetches a flow of paginated news articles from various sources.
     *
     * @param sources List of news sources to fetch articles from.
     * @return A [Flow] of paginated [Article] objects.
     */
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    /**
     * Fetches a flow of paginated news articles based on a search query.
     *
     * @param searchQuery The query string for searching news articles.
     * @param sources List of news sources to filter the search.
     * @return A [Flow] of paginated [Article] objects.
     */
    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    newsApi = newsApi,
                    searchQuery = searchQuery,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    /**
     * Inserts or updates a news article in the local database.
     *
     * @param article The [Article] object to be inserted or updated.
     */
    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article = article)
    }

    /**
     * Deletes a news article from the local database.
     *
     * @param article The [Article] object to be deleted.
     */
    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article = article)
    }

    /**
     * Fetches a flow of all saved news articles from the local database.
     *
     * @return A [Flow] of a list of [Article] objects.
     */
    override fun selectArticles(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

    /**
     * Fetches a specific news article from the local database based on its URL.
     *
     * @param url The URL of the article to be retrieved.
     * @return The [Article] object corresponding to the given URL, or null if not found.
     */
    override suspend fun selectArticle(url: String): Article? {
        return newsDao.getArticle(url = url)
    }
}