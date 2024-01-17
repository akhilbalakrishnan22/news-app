/*
 * This file is part of the NewsApp application.
 * It defines the constants used throughout the application.
 */
package com.example.newsapp.util

import com.example.newsapp.BuildConfig

/**
 * Object containing constants used in NewsApp application.
 */
object Constants {

    /**
     * Shared preferences key for storing user settings.
     */
    const val USER_SETTINGS = "userSettings"

    /**
     * Shared preferences key for storing the app entry information.
     */
    const val APP_ENTRY = "appEntry"

    /**
     * API key for accessing News API.
     * Obtained from BuildConfig.API_KEY
     */
    const val API_KEY = BuildConfig.API_KEY

    /**
     * Base URL for News API
     */
    const val BASE_URL = "https://newsapi.org/v2/"

    /**
     * Database name for the news database.
     */
    const val NEWS_DATABASE_NAME = "news_db"

}