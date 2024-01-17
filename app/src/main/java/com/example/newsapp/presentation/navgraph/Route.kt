/*
 * This file is part of the NewsApp application.
 * It defines navigation routes with in the app.
 */
package com.example.newsapp.presentation.navgraph

/**
 * Sealed class representing different routes within the app.
 *
 * @param route The string representation of the route.
 */
sealed class Route(
    val route: String
) {
    /**
     * Represents the onboarding screen route.
     */
    data object OnBoardingScreen : Route(route = "onBoardingScreen")

    /**
     * Represents the home screen route.
     */
    data object HomeScreen : Route(route = "homeScreen")

    /**
     * Represents the search screen route.
     */
    data object SearchScreen : Route(route = "searchScreen")

    /**
     * Represents the bookmark screen route.
     */
    data object BookmarkScreen : Route(route = "bookmarkScreen")

    /**
     * Represents the details screen route.
     */
    data object DetailsScreen : Route(route = "detailsScreen")

    /**
     * Represents the initial app start navigation route.
     */
    data object AppStartNavigation : Route(route = "appStartNavigation")

    /**
     * Represents the news navigation route.
     */
    data object NewsNavigation : Route(route = "newsNavigation")

    /**
     * Represents the news navigator screen route.
     */
    data object NewsNavigatorScreen : Route(route = "newsNavigator")
}