/*
 * This file is part of the NewsApp application.
 * It defines the HomeViewModel class, responsible for managing data and business logic for the Home screen.
 */
package com.example.newsapp.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for the Home screen in the NewsApp application.
 * Manages data and business logic related to displaying news articles on the Home screen.
 *
 * This ViewModel is integrated with the Hilt dependency injection framework using the @HiltViewModel annotation.
 * The @Inject annotation is used to specify dependencies that should be injected into the ViewModel.
 * It provides a mutable state for the Home screen and retrieves news data using the NewsUseCases.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    /**
     * Mutable state representing the current state of the Home screen.
     * It includes the current scroll value and the maximum scroll value.
     */
    var state = mutableStateOf(HomeState())
        private set

    /**
     * LiveData representing the list of news articles fetched from the NewsUseCases.
     * It is cached in the viewModelScope to survive configuration changes.
     */
    val news = newsUseCases.getNews(
        sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
    ).cachedIn(viewModelScope)

    /**
     * Handles events triggered by the Home screen.
     *
     * @param event The event representing a user action or interaction on the Home screen.
     */
    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.UpdateScrollValue -> {
                // Update the scroll value in the Home screen state
                updateScrollValue(event.newValue)
            }

            is HomeEvent.UpdateMaxScrollValue -> {
                // Update the maximum scroll value in the Home screen state
                updateMaxScrollValue(event.newValue)
            }
        }
    }

    /**
     * Updates the maximum scroll value in the Home screen state.
     *
     * @param newValue The new maximum scroll value to be updated.
     */
    private fun updateMaxScrollValue(newValue: Int) {
        state.value = state.value.copy(scrollValue = newValue)
    }

    /**
     * Updates the current scroll value in the Home screen state.
     *
     * @param newValue The new scroll value to be updated.
     */
    private fun updateScrollValue(newValue: Int) {
        state.value = state.value.copy(maxScrollValue = newValue)
    }
}