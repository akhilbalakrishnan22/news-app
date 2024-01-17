/*
 * This file is part of the NewsApp application.
 * It defines navigation graph with in the app.
 */
package com.example.newsapp.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.presentation.news_navigator.NewsNavigator
import com.example.newsapp.presentation.onboarding.OnBoardingScreen
import com.example.newsapp.presentation.onboarding.OnBoardingViewModel

/**
 * Composable function defining the navigation graph for the app.
 *
 * @param startDestination The starting destination of the navigation graph.
 */
@Composable
fun NavGraph(
    startDestination: String
) {
    // Create a navigation controller to manage navigation within the graph
    val navController = rememberNavController()

    // Define the navigation graph using NavHost
    NavHost(navController = navController, startDestination = startDestination) {
        // Navigation for the onboarding process
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = viewModel::onEvent
                )
            }
        }

        // Navigation for the main news section
        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(
                route = Route.NewsNavigatorScreen.route
            ) {
                NewsNavigator()
            }
        }
    }
}