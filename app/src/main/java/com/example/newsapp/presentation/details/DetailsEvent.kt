/*
 * This file is part of the NewsApp application.
 * It defines the DetailsEvent sealed class, representing events related to the Details screen.
 */
package com.example.newsapp.presentation.details

import com.example.newsapp.domain.models.Article

/**
 * Sealed class defining events for the Details screen in the NewsApp application.
 */
sealed class DetailsEvent {

    /**
     * Event to insert,update or delete an article in the Details screen.
     *
     * @property article The article associated with the event.
     */
    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()

    /**
     * Event to remove any side effects in the Details screen.
     */
    data object RemoveSideEffect : DetailsEvent()

}