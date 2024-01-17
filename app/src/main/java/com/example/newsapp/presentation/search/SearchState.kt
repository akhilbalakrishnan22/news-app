/*
 * This file is part of the NewsApp application.
 * It defines the SearchState data class, representing the state of the search screen.
 */
package com.example.newsapp.presentation.search

import androidx.paging.PagingData
import com.example.newsapp.domain.models.Article
import kotlinx.coroutines.flow.Flow

/**
 * Data class representing the state of the search screen in the application.
 *
 * @property searchQuery The current search query entered by the user.
 * @property articles A Flow of PagingData representing the search results.
 */
data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)