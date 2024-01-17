/*
 * This file is part of the NewsApp application.
 * It defines the MainViewModel, responsible for managing data and business logic for MainActivity.
 */
package com.example.newsapp.presentation.main_activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.example.newsapp.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * ViewModel for MainActivity in the NewsApp application.
 * Manages data and business logic related to the main activity.
 *
 * This ViewModel is integrated with the Hilt dependency injection framework using the @HiltViewModel annotation.
 * The @Inject annotation is used to specify dependencies that should be injected into the ViewModel.
 * The provided dependencies are used to initialize the ViewModel and manage app entry states.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    /**
     * State indicating whether the splash screen should be displayed.
     */
    var splashCondition by mutableStateOf(true)
        private set

    /**
     * The starting destination for the navigation graph.
     */
    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    /**
     * Initialize the ViewModel by reading the app entry state.
     * Observes changes in the app entry state using the provided use case.
     * Updates the starting destination and splash condition accordingly.
     */
    init {
        appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
            // Determine the starting destination based on the app entry state
            if (shouldStartFromHomeScreen) {
                startDestination = Route.NewsNavigation.route
            } else {
                startDestination = Route.AppStartNavigation.route
            }

            // Add a delay to simulate a smooth transition and update the splash condition
            delay(300)
            splashCondition = false
        }.launchIn(viewModelScope)
    }
}