/*
 * This package contains components related to the Bookmark feature in the News App.
 * It includes the [BookmarkState] data class, representing the state for the Bookmark screen.
 */
package com.example.newsapp.presentation.bookmark

import com.example.newsapp.domain.models.Article

/**
 * Data class representing the state for the Bookmark screen.
 *
 * @property articles List of bookmarked articles to be displayed on the Bookmark screen.
 */
data class BookmarkState(
    val articles: List<Article> = emptyList()
)