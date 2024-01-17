/*
 * This file is part of the NewsApp application.
 * It defines the functions for data access operations with local database.
 */
package com.example.newsapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.domain.models.Article
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) interface for interacting with the local database.
 * Defines methods for inserting, updating, deleting, and querying news articles.
 */
@Dao
interface NewsDao {

    /**
     * Inserts or updates a news article in the local database.
     *
     * @param article The [Article] object to be inserted or updated.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article)

    /**
     * Deletes a news article from the local database.
     *
     * @param article The [Article] object to be deleted.
     */
    @Delete
    suspend fun delete(article: Article)

    /**
     * Fetches a flow of all saved news articles from the local database.
     *
     * @return A [Flow] of a list of [Article] objects.
     */
    @Query("SELECT * FROM Article")
    fun getArticles(): Flow<List<Article>>

    /**
     * Fetches a specific news article from the local database based on its URL.
     *
     * @param url The URL of the article to be retrieved.
     * @return The [Article] object corresponding to the given URL, or null if not found.
     */
    @Query("SELECT * FROM Article WHERE url=:url")
    suspend fun getArticle(url: String): Article?

}