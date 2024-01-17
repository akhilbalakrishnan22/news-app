/*
 * This file is part of the NewsApp application.
 * It defines the type converts that used in NewsApp.
 */
package com.example.newsapp.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.newsapp.domain.models.Source

/**
 * Type converter class for converting custom types in the NewsApp database.
 * This class handles conversion between Source objects and their string representation.
 */
@ProvidedTypeConverter
class NewsTypeConverter {

    /**
     * Converts a [Source] object to its string representation for database storage.
     *
     * @param source The Source object to be converted.
     * @return A string representation of the Source object.
     */
    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.id}, ${source.name}"
    }

    /**
     * Converts a string representation back to a [Source] object.
     *
     * @param source The string representation of the Source object.
     * @return The Source object reconstructed from the string representation.
     */
    @TypeConverter
    fun stringToSource(source: String): Source {
        return source.split(",").let { sourceArray ->
            Source(sourceArray[0], sourceArray[1])
        }
    }
}