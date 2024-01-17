/*
 * This file is part of the NewsApp application.
 * It defines an interface for managing news-related data in the NewsApp.
 */
package com.example.newsapp.domain.repository

/**
 * Interface defining the contract for interacting with news-related data in the application.
 */
import androidx.paging.PagingData
import com.example.newsapp.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    /**
     * Retrieves a flow of paged news articles based on the provided sources.
     *
     * @param sources List of source identifiers to filter the news articles.
     * @return A [Flow] emitting [PagingData] of [Article] objects.
     */
    fun getNews(sources: List<String>): Flow<PagingData<Article>>

    /**
     * Searches for news articles based on the provided query and sources.
     *
     * @param searchQuery The search query string.
     * @param sources List of source identifiers to filter the news articles.
     * @return A [Flow] emitting [PagingData] of [Article] objects.
     */
    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>

    /**
     * Inserts or updates an article in the repository.
     *
     * @param article The [Article] object to be inserted or updated.
     */
    suspend fun upsertArticle(article: Article)

    /**
     * Deletes an article from the repository.
     *
     * @param article The [Article] object to be deleted.
     */
    suspend fun deleteArticle(article: Article)

    /**
     * Retrieves a flow of selected articles.
     *
     * @return A [Flow] emitting a list of [Article] objects.
     */
    fun selectArticles(): Flow<List<Article>>

    /**
     * Selects a single article based on its URL.
     *
     * @param url The URL of the article.
     * @return The [Article] object corresponding to the provided URL, or null if not found.
     */
    suspend fun selectArticle(url: String): Article?

}