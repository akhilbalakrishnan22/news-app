/*
 * This file is part of the NewsApp application.
 * It defines the local manager for handling app entry state in NewsApp data layer.
 */
package com.example.newsapp.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.newsapp.domain.manager.LocalUserManager
import com.example.newsapp.util.Constants
import com.example.newsapp.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementation of the [LocalUserManager] interface, providing methods to manage
 * user-specific local data using the Android DataStore API.
 *
 * @param context The application context used to access the DataStore.
 */
class LocalUserManagerImpl(
    private val context: Context
) : LocalUserManager {

    /**
     * Saves the app entry state to the DataStore.
     */
    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings ->
            settings[PreferencesKeys.APP_ENTRY] = true
        }
    }

    /**
     * Reads the app entry state from the DataStore.
     *
     * @return A [Flow] emitting the app entry state as a [Boolean].
     */
    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.APP_ENTRY] ?: false
        }
    }
}

/**
 * Extension property to access the DataStore for preferences.
 */
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

/**
 * Object containing preferences keys for the DataStore.
 */
private object PreferencesKeys {
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}