/*
 * This file is part of the NewsApp application.
 * It defines the navigation graph for navigating with in the app after setting the app entry(onboarding).
 */
package com.example.newsapp.presentation.news_navigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.data.manager.ConnectionState
import com.example.newsapp.data.manager.NewsConnectivityManager
import com.example.newsapp.domain.models.Article
import com.example.newsapp.presentation.bookmark.BookmarkScreen
import com.example.newsapp.presentation.bookmark.BookmarkViewModel
import com.example.newsapp.presentation.details.DetailsEvent
import com.example.newsapp.presentation.details.DetailsScreen
import com.example.newsapp.presentation.details.DetailsViewModel
import com.example.newsapp.presentation.home.HomeScreen
import com.example.newsapp.presentation.home.HomeViewModel
import com.example.newsapp.presentation.navgraph.Route
import com.example.newsapp.presentation.news_navigator.components.BottomNavigationItem
import com.example.newsapp.presentation.news_navigator.components.NewsBottomNavigation
import com.example.newsapp.presentation.search.SearchScreen
import com.example.newsapp.presentation.search.SearchViewModel
import kotlinx.coroutines.launch

/**
 * Composable function representing the main navigation structure for the news section.
 *
 * @param newsConnectivityManager An instance of [NewsConnectivityManager] to monitor internet connectivity.
 */
@Composable
fun NewsNavigator(
    newsConnectivityManager: NewsConnectivityManager
) {

    // Define bottom navigation items
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark")
        )
    }

    val navController = rememberNavController()

    // Get the current back stack entry for tracking the selected item
    val backStackState = navController.currentBackStackEntryAsState().value

    // Remember the selected item using saveable state
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    // Update the selected item based on the current destination route
    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookmarkScreen.route -> 2
            else -> 0
        }
    }

    // Determine if the bottom navigation bar should be visible based on the current destination
    val isBottomNavigationBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.BookmarkScreen.route
    }

    val coroutineScope = rememberCoroutineScope()
    // Create a SnackBarHostState to manage and display SnackBars
    val snackBarHostState = remember { SnackbarHostState() }
    val noInternetConnectionText = stringResource(id = R.string.no_internet_connection)

    // Collect internet connectivity state and show SnackBar when there is no internet
    LaunchedEffect(key1 = true) {
        newsConnectivityManager.connectionState.collect { connectionState ->
            when (connectionState) {
                is ConnectionState.Connected -> snackBarHostState.currentSnackbarData?.dismiss()

                is ConnectionState.NoInternet -> {
                    coroutineScope.launch {
                        // Show SnackBar when there is no internet connection
                        snackBarHostState.showSnackbar(
                            message = noInternetConnectionText,
                            duration = SnackbarDuration.Indefinite
                        )
                    }
                }

                is ConnectionState.Unknown -> snackBarHostState.currentSnackbarData?.dismiss()
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomNavigationBarVisible) {
                NewsBottomNavigation(
                    items = bottomNavigationItems,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateOnTap(
                                navController = navController,
                                route = Route.HomeScreen.route
                            )

                            1 -> navigateOnTap(
                                navController = navController,
                                route = Route.SearchScreen.route
                            )

                            2 -> navigateOnTap(
                                navController = navController,
                                route = Route.BookmarkScreen.route
                            )
                        }
                    }
                )
            }
        }
    ) {
        // Calculate bottom padding for the NavHost
        val bottomPadding = it.calculateBottomPadding()

        // NavHost for handling navigation between composables
        NavHost(
            modifier = Modifier.padding(bottom = bottomPadding),
            navController = navController,
            startDestination = Route.HomeScreen.route
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                val state = viewModel.state.value
                HomeScreen(
                    state = state,
                    event = viewModel::onEvent,
                    articles = articles,
                    navigateToSearch = {
                        navigateOnTap(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                    },
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }

            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }

            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                if (viewModel.sideEffect != null) {
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_LONG)
                        .show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                        DetailsScreen(
                            article = article,
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() }
                        )
                    }
            }

            composable(route = Route.BookmarkScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookmarkScreen(
                    state = state,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }
        }
    }
}

/**
 * Navigates to a destination when an item in the bottom navigation bar is tapped.
 *
 * @param navController NavController to manage navigation.
 * @param route Destination route to navigate to.
 */
private fun navigateOnTap(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

/**
 * Navigates to the Details screen with the specified article.
 *
 * @param navController NavController to manage navigation.
 * @param article Article to be displayed in the Details screen.
 */
private fun navigateToDetails(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(route = Route.DetailsScreen.route)
}