/*
 * This file is part of the NewsApp application.
 * It defines the HomeEvent sealed class, representing events related to the home screen.
 */
package com.example.newsapp.presentation.home

/**
 * Sealed class representing events that can occur on the Home screen.
 * Each event is a subclass of HomeEvent, allowing for exhaustive when statements.
 */
sealed class HomeEvent {

    /**
     * Data class representing the event to update the scroll value on the Home screen.
     *
     * @property newValue The new scroll value to be updated on the Home screen.
     */
    data class UpdateScrollValue(val newValue: Int) : HomeEvent()

    /**
     * Data class representing the event to update the maximum scroll value on the Home screen.
     *
     * @property newValue The new maximum scroll value to be updated on the Home screen.
     */
    data class UpdateMaxScrollValue(val newValue: Int) : HomeEvent()

}