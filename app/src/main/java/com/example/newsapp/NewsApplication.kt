/*
 * This file is part of the NewsApp application.
 * It defines the NewsApplication class, which extends the Application.
 * The class is annotated with @HiltAndroidApp for integrating Dagger Hilt dependency injection.
 */
package com.example.newsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * The main application class for the NewsApp.
 * Extends the Android Application class to handle application-level functionality.
 *
 * @See Application
 */
@HiltAndroidApp
class NewsApplication : Application()