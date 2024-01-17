/*
 * This file is part of the NewsApp application
 * This package contains the domain manager for local user data in the NewsApp.
 * The [LocalUserManager] interface defines methods for saving and reading app entry data.
 */
package com.example.newsapp.domain.manager

import kotlinx.coroutines.flow.Flow

/**
 * An interface for managing local user data related to app entry in the NewsApp.
 */
interface LocalUserManager {

    /**
     * Saves the app entry data asynchronously. This method should be called from a coroutine.
     */
    suspend fun saveAppEntry()

    /**
     * Reads the app entry data as a flow of boolean values.
     *
     * @return A [Flow] representing the app entry data. True indicates that the user has
     * previously entered the app, while false indicates a new entry.
     */
    fun readAppEntry(): Flow<Boolean>

}