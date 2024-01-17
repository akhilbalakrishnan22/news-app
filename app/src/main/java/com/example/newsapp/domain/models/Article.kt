/*
 * This file is part of the NewsApp application
 * It defines data class for article.
 */
package com.example.newsapp.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Represents a data model for an article in the NewsApp.
 * Implements [Parcelable] for easy object serialization/deserialization.
 *
 * @property author The author of the article. Can be null.
 * @property content The content or main text of the article.
 * @property description A short description or summary of the article.
 * @property publishedAt The timestamp indicating when the article was published.
 * @property source The source of the article, represented by a [Source] object.
 * @property title The title of the article.
 * @property url The unique URL of the article. Serves as the primary key in the Room database.
 * @property urlToImage The URL to the image associated with the article.
 */
@Parcelize
@Entity
data class Article(
    val author: String?,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    @PrimaryKey val url: String,
    val urlToImage: String
) : Parcelable