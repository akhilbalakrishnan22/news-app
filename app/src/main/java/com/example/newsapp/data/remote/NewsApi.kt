/*
 * This file is part of the NewsApp application.
 * It defines interface for interacting with the news API.
 */
package com.example.newsapp.data.remote

import com.example.newsapp.data.remote.dto.NewsResponse
import com.example.newsapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit API interface for interacting with the News API.
 */
interface NewsApi {

    /**
     * Fetches news articles from the News API based on specified sources.
     *
     * @param page The page number for paginated results.
     * @param sources Comma-separated list of news sources to fetch articles from.
     * @param apiKey The API key for authentication (default is the app's API key).
     * @return A [NewsResponse] containing a list of articles, API status, and total results.
     */
    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

    /**
     * Searches for news articles from the News API based on a query string.
     *
     * @param searchQuery The query string for searching news articles.
     * @param page The page number for paginated results.
     * @param sources Comma-separated list of news sources to filter the search.
     * @param apiKey The API key for authentication (default is the app's API key).
     * @return A [NewsResponse] containing a list of articles, API status, and total results.
     */
    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

}