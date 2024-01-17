/*
 * This file is part of the NewsApp application.
 * It defines the BookmarkViewModel class, responsible for managing data and business logic for the Bookmark screen.
 */
package com.example.newsapp.presentation.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * View model responsible for managing the UI-related data of the Bookmark screen.
 *
 * @property newsUseCases The use cases related to news articles.
 * @property _state Mutable state representing the current state of the Bookmark screen.
 * @property state The state exposed as an immutable [State] to observe changes in the UI.
 */
@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    private val _state = mutableStateOf(BookmarkState())
    val state: State<BookmarkState> = _state

    /**
     * Initializes the [BookmarkViewModel] and triggers the retrieval of bookmarked articles.
     */
    init {
        getArticles()
    }

    /**
     * Fetches the bookmarked articles from the data source and updates the state accordingly.
     */
    private fun getArticles() {
        newsUseCases.selectArticles().onEach { articles ->
            // Reverses the order of articles before updating the state to show the latest first.
            _state.value = state.value.copy(articles = articles.reversed())
        }.launchIn(viewModelScope)
    }
}