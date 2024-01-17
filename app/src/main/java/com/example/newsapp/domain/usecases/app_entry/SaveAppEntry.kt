/*
 * This file is part of the NewsApp application.
 * It defines use case related to managing the app entry state in the NewsApp domain.
 */
package com.example.newsapp.domain.usecases.app_entry

import com.example.newsapp.domain.manager.LocalUserManager

/**
 * Use case responsible for saving the app entry state.
 *
 * This use case saves the app entry state by delegating the operation to a [LocalUserManager].
 *
 * @property localUserManager The manager responsible for handling local user data.
 */
class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {

    /**
     * Invokes the use case, initiating the process of saving the app entry state.
     */
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}