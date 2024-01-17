/*
 * This file is part of the NewsApp application.
 * It defines the use case related to managing the app entry state in the NewsApp domain.
 */
package com.example.newsapp.domain.usecases.app_entry

import com.example.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

/**
 * Use case responsible for reading the app entry state.
 *
 * This use case retrieves the app entry state by delegating the operation to a [LocalUserManager].
 *
 * @property localUserManager The manager responsible for handling local user data.
 */
class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {

    /**
     * Invokes the use case, initiating the process of reading the app entry state.
     *
     * @return A [Flow] emitting a boolean value representing the app entry state.
     */
    operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}