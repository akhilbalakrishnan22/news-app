/*
 * This file is part of the NewsApp application.
 * It defines the OnBoardingViewModel, responsible for managing data and business logic for the onboarding screen.
 */
package com.example.newsapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.usecases.app_entry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the Onboarding screen in the NewsApp application.
 * Manages data and business logic related to the onboarding experience.
 *
 * This ViewModel is integrated with the Hilt dependency injection framework using the @HiltViewModel annotation.
 * The @Inject annotation is used to specify dependencies that should be injected into the ViewModel.
 * It provides methods to handle events triggered by the onboarding screen, such as saving the app entry state.
 */
@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    /**
     * Handles events triggered by the onboarding screen.
     *
     * @param event The event representing a user action or interaction on the onboarding screen.
     */
    fun onEvent(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.SaveAppEntry -> {
                // Save app entry state when requested by the onboarding screen.
                saveAppEntry()
            }
        }
    }

    /**
     * Saves the app entry state using the associated use case.
     * The operation is performed in a coroutine scope to ensure proper handling of asynchronous tasks.
     */
    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry()
        }
    }
}