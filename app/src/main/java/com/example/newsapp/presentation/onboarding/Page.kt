/*
 * This file is part of the NewsApp application.
 * It defines the Page data class and a list of onboarding pages.
 */
package com.example.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.newsapp.R

/**
 * Data class representing an onboarding page.
 *
 * @property title The title of the onboarding page.
 * @property description The description or content of the onboarding page.
 * @property image The image resource ID associated with the onboarding page.
 */
data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

/**
 * List of predefined onboarding pages for the NewsApp.
 *
 * Each page is represented by a Page data class containing title, description, and image resource ID.
 */
val pages = listOf(
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding3
    )
)