/*
 * This file is part of the NewsApp application.
 * It defines the structure of response from the news API.
 */
package com.example.newsapp.data.remote.dto

import com.example.newsapp.domain.models.Article

/**
 * Data class representing the response received from a news API.
 *
 * @property articles List of [Article] objects containing news information.
 * @property status String representing the status of the API response.
 * @property totalResults Total number of results available from the API.
 */
data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)