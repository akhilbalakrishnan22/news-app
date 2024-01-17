/*
 * This file is part of the NewsApp application
 * It defines data class for source.
 */
package com.example.newsapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Represents a data model for the source of an article in the NewsApp.
 * Implements [Parcelable] for easy object serialization/deserialization.
 *
 * @property id The unique identifier of the source.
 * @property name The name of the source.
 */
@Parcelize
data class Source(
    val id: String,
    val name: String
) : Parcelable