/*
 * This file is part of the NewsApp application.
 * It defines list of custom composable to display an empty screen used in the app.
 */
package com.example.newsapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import com.example.newsapp.R
import com.example.newsapp.ui.theme.NewsAppTheme
import java.net.ConnectException
import java.net.SocketTimeoutException

/**
 * Composable function to display an empty screen with a message and an icon, typically used for error states.
 *
 * @param error The [LoadState.Error] that occurred, providing information about the error.
 */
@Composable
fun EmptyScreen(error: LoadState.Error? = null) {

    // Derive the error message from the provided LoadState.Error or set a default message
    var message by remember {
        mutableStateOf(parseErrorMessage(error = error))
    }

    // Choose the appropriate icon based on the error type or set a default icon
    var icon by remember {
        mutableIntStateOf(R.drawable.ic_network_error)
    }

    if (error == null) {
        message = "You have not saved news so far !"
        icon = R.drawable.ic_search_document
    }

    // State variable to control the start of the alpha animation
    var startAnimation by remember {
        mutableStateOf(false)
    }

    // Animate the alpha value based on the startAnimation state
    val alphaAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 0.3f else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "background animation"
    )

    // Launch an effect to start the animation when the composable is initially composed
    LaunchedEffect(key1 = true) {
        startAnimation = true
    }

    // Call the EmptyContent composable to render the actual content with the derived properties
    EmptyContent(alphaAnim = alphaAnimation, message = message, iconId = icon)

}

/**
 * Composable function to display the content of an empty screen with an animated icon and a message.
 *
 * @param alphaAnim The alpha value for animating the content.
 * @param message The message to be displayed on the empty screen.
 * @param iconId The resource ID of the icon to be displayed on the empty screen.
 */
@Composable
fun EmptyContent(alphaAnim: Float, message: String, iconId: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = if (isSystemInDarkTheme()) LightGray else DarkGray,
            modifier = Modifier
                .size(120.dp)
                .alpha(alphaAnim)
        )
        Text(
            modifier = Modifier
                .padding(10.dp)
                .alpha(alphaAnim),
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = if (isSystemInDarkTheme()) LightGray else DarkGray,
        )
    }
}

/**
 * Function to parse and generate a user-friendly error message based on the given [LoadState.Error].
 *
 * @param error The [LoadState.Error] containing information about the error.
 * @return A string representing a user-friendly error message.
 */
fun parseErrorMessage(error: LoadState.Error?): String {
    // Use a when expression to handle different types of errors
    return when (error?.error) {
        is SocketTimeoutException -> {
            "Server Unavailable." // Return message for a SocketTimeoutException
        }

        is ConnectException -> {
            "Internet Unavailable." // Return message for a ConnectException
        }

        else -> {
            "Unknown Error." // Default message for other types of errors
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun EmptyScreenPreview() {
    NewsAppTheme {
        Surface {
            EmptyContent(
                alphaAnim = 0.3f,
                message = "Internet Unavailable.",
                R.drawable.ic_network_error
            )
        }
    }
}