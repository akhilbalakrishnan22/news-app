/*
 * This file is part of the NewsApp application.
 * It defines custom-styled buttons used in the app.
 */
package com.example.newsapp.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.ui.theme.WhiteGray

/**
 * Composable function for a custom-styled primary button.
 *
 * @param text The text to be displayed on the button.
 * @param onClick The callback function to be executed when the button is clicked.
 */
@Composable
fun NewsButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
        )
    }
}

/**
 * Composable function for a custom-styled text button.
 *
 * @param text The text to be displayed on the button.
 * @param onClick The callback function to be executed when the button is clicked.
 */
@Composable
fun NewsTextButton(
    text: String,
    onClick: () -> Unit
) {
    TextButton(onClick = onClick) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = WhiteGray
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    NewsAppTheme {
        Surface {
            Column {
                NewsTextButton(text = "Back", onClick = {})
                NewsButton(text = "Next", onClick = {})
                NewsButton(text = "Get Started", onClick = {})
            }
        }
    }
}