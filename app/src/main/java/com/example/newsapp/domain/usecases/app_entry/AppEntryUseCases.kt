/*
 * This file is part of the NewsApp application.
 * It defines use cases related to managing the app entry state in the NewsApp domain.
 */
package com.example.newsapp.domain.usecases.app_entry

/**
 * Data class representing a collection of app entry use cases in the NewsApp domain.
 *
 * @property readAppEntry The use case for reading the app entry state.
 * @property saveAppEntry The use case for saving the app entry state.
 */
data class AppEntryUseCases(
    val readAppEntry: ReadAppEntry,
    val saveAppEntry: SaveAppEntry
)