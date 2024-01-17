/*
 * This file is part of the NewsApp application.
 * It defines the SearchViewModel class, responsible for handling the search screen's logic and managing its state.
 */
package com.example.newsapp.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel class for the search screen in the application.
 *
 * @property newsUseCases Injected dependency for handling news-related use cases.
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    /**
     * The mutable state representing the current state of the search screen.
     */
    private val _state = mutableStateOf(SearchState())

    /**
     * The immutable state exposed to observers, providing access to the current search screen state.
     */
    val state: State<SearchState> = _state

    /**
     * Function to handle incoming search-related events.
     *
     * @param event The search event triggering a state change.
     */
    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                _state.value = state.value.copy(searchQuery = event.searchQuery)
            }

            is SearchEvent.SearchNews -> {
                searchNews()
            }
        }
    }

    /**
     * Function to initiate a news search based on the current search query and sources.
     */
    private fun searchNews() {
        val articles = newsUseCases.searchNews(
            searchQuery = state.value.searchQuery,
            sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
        ).cachedIn(viewModelScope)
        _state.value = state.value.copy(articles = articles)
    }
}