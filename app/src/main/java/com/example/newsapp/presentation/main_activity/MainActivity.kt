/*
 * This file is part of the NewsApp application.
 * It defines the MainActivity, the main entry point for the NewsApp UI.
 */
package com.example.newsapp.presentation.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.example.newsapp.presentation.navgraph.NavGraph
import com.example.newsapp.ui.theme.NewsAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main activity of the NewsApp application.
 * Entry point for the UI with Dagger Hilt integration.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // ViewModel for MainActivity
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configure window to extend into the system bars
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Set up the splash screen with a condition to keep it on screen
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashCondition
            }
        }

        // Set the content of the activity using Jetpack Compose
        setContent {

            // Apply the overall theme of the app
            NewsAppTheme {

                // Apply the overall theme of the app
                val isSystemDarkTheme = isSystemInDarkTheme()
                val systemUiController = rememberSystemUiController()

                // Apply side effect to control system UI colors
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemDarkTheme
                    )
                }

                // Compose UI structure
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Define the starting destination for the navigation graph
                    val startDestination = viewModel.startDestination

                    // Create and display the navigation graph
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }
}