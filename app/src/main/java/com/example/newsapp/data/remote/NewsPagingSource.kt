/*
 * This file is part of the NewsApp application.
 * It defines the Paging source for loading news articles from news API.
 */
package com.example.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.domain.models.Article

/**
 * Paging source for loading news articles from the News API using the Paging 3 library.
 *
 * @param newsApi The Retrofit API interface for fetching news articles.
 * @param sources Comma-separated list of news sources to fetch articles from.
 */
class NewsPagingSource(
    private val newsApi: NewsApi,
    private val sources: String
) : PagingSource<Int, Article>() {

    private var totalNewsCount = 0

    /**
     * Loads a chunk of data from the underlying data source based on provided parameters.
     *
     * @param params Load parameters, including the page key.
     * @return A [LoadResult] containing the loaded data and next/previous keys.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            // Fetch news articles from the News API
            val newsResponse = newsApi.getNews(sources = sources, page = page)
            // Update the total count of news articles
            totalNewsCount += newsResponse.articles.size
            // Remove duplicates from the fetched articles using distinctBy
            val articles =
                newsResponse.articles.distinctBy { it.title } // distinctBy: removes duplicates
            // Return the loaded data and keys
            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            // Return an error result if there's an exception during loading
            LoadResult.Error(
                throwable = e
            )
        }
    }

    /**
     * Returns a refresh key to determine when to invalidate and re-fetch data.
     *
     * @param state The current [PagingState].
     * @return The refresh key.
     */
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition = anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}