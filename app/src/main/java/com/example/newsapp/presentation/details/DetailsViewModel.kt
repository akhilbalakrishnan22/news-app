/*
 * This file is part of the NewsApp application.
 * It defines the DetailsViewModel class, responsible for managing the business logic and state
 * of the Details screen in the NewsApp application.
 */
package com.example.newsapp.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class for the Details screen in the NewsApp application.
 *
 * @param newsUseCases Injected dependency for accessing news-related use cases.
 */
@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    /**
     * Mutable state to hold a string representing any side effect or notification to be displayed
     * on the Details screen. It is initially set to null.
     */
    var sideEffect by mutableStateOf<String?>(null)
        private set

    /**
     * Function to handle events triggered by user interactions on the Details screen.
     *
     * @param event The event to be processed.
     */
    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    // Check if the article already exists in the database
                    val article = newsUseCases.selectArticle(event.article.url)
                    // If the article does not exist, upsert(insert or update) it; otherwise, delete it
                    if (article == null) {
                        upsertArticle(event.article)
                    } else {
                        deleteArticle(event.article)
                    }
                }
            }

            is DetailsEvent.RemoveSideEffect -> {
                // Clear the side effect, after displaying a notification
                sideEffect = null
            }
        }
    }

    /**
     * Coroutine function to delete an article from the database and set a notification message.
     *
     * @param article The article to be deleted.
     */
    private suspend fun deleteArticle(article: Article) {
        newsUseCases.deleteArticle(article = article)
        sideEffect = "Article deleted"
    }

    /**
     * Coroutine function to upsert (insert or update) an article in the database and set a
     * notification message.
     *
     * @param article The article to be upserted.
     */
    private suspend fun upsertArticle(article: Article) {
        newsUseCases.upsertArticle(article = article)
        sideEffect = "Article saved"
    }
}