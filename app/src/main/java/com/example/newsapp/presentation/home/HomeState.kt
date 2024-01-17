/*
 * This file is part of the NewsApp application.
 * It defines the HomeState data class, representing the state of the Home screen.
 */
package com.example.newsapp.presentation.home

/**
 * Data class representing the state of the Home screen in the NewsApp application.
 *
 * @property scrollValue The current scroll value of the Home screen.
 * @property maxScrollValue The maximum scroll value allowed on the Home screen.
 */
data class HomeState(
    val scrollValue: Int = 0,
    val maxScrollValue: Int = 0
)