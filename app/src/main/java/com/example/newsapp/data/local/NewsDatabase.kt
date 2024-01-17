/*
 * This file is part of the NewsApp application.
 * It defines the database of NewsApp.
 */
package com.example.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.domain.models.Article

/**
 * Room Database class representing the local database for storing news articles.
 *
 * @property entities List of entity classes to be included in the database (e.g., [Article]).
 * @property version The version number of the database schema.
 * @property newsDao The Data Access Object (DAO) for accessing news articles in the database.
 */
@Database(entities = [Article::class], version = 1)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDatabase : RoomDatabase() {

    /**
     * Provides access to the [NewsDao] for interacting with news articles in the database.
     */
    abstract val newsDao: NewsDao

}