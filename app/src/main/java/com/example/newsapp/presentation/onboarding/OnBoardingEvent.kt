/*
 * This file is part of the NewsApp application.
 * It defines the OnBoardingEvent sealed class, representing events for the onboarding screen.
 */
package com.example.newsapp.presentation.onboarding

/**
 * Sealed class representing events that can occur on the Onboarding screen.
 * Each event is a subclass of OnBoardingEvent, allowing for exhaustive when statements.
 */
sealed class OnBoardingEvent {

    /**
     * Object representing the event to save the app entry state.
     * This event is triggered when the user takes an action to save the onboarding completion status.
     */
    data object SaveAppEntry : OnBoardingEvent()

}