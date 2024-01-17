/*
 * This file is part of the NewsApp application.
 * It defines the PageIndicator composable, responsible for rendering a visual indicator for onboarding pages.
 */
package com.example.newsapp.presentation.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.example.newsapp.presentation.Dimens.IndicatorSize
import com.example.newsapp.ui.theme.BlueGray

/**
 * Composable function representing a visual page indicator for onboarding screens.
 *
 * @param modifier The modifier for styling and layout customization.
 * @param pageSize The total number of pages in the onboarding screen.
 * @param selectedPage The index of the currently selected page.
 * @param selectedColor The color of the indicator for the selected page.
 * @param unSelectedColor The color of the indicator for unselected pages.
 */
@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unSelectedColor: Color = BlueGray
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(pageSize) { page ->
            Box(
                modifier = Modifier
                    .size(IndicatorSize)
                    .clip(CircleShape)
                    .background(
                        color = if (page == selectedPage) selectedColor else unSelectedColor
                    )
            )
        }
    }
}