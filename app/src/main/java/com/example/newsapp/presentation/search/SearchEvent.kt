/*
 * This file is part of the NewsApp application.
 * It defines the SearchEvent sealed class, representing events related to the search functionality.
 */
package com.example.newsapp.presentation.search

/**
 * Sealed class representing events related to the search functionality in the application.
 */
sealed class SearchEvent {

    /**
     * Data class for updating the search query.
     *
     * @property searchQuery The new search query to be applied.
     */
    data class UpdateSearchQuery(val searchQuery: String) : SearchEvent()

    /**
     * Object representing the event of initiating a news search.
     */
    data object SearchNews : SearchEvent()

}