/*
 * This file is part of the NewsApp application.
 * It defines the UI components for displaying the bottom navigation bar for navigating with in the app.
 */
package com.example.newsapp.presentation.news_navigator.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.presentation.Dimens.ExtraSmallPadding2
import com.example.newsapp.presentation.Dimens.IconSize
import com.example.newsapp.ui.theme.NewsAppTheme

/**
 * Data class representing a single item in the bottom navigation bar.
 *
 * @param icon Drawable resource ID for the icon.
 * @param text Text label for the navigation item.
 */
data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String
)

/**
 * Composable function representing the custom bottom navigation bar for the news section.
 *
 * @param items List of [BottomNavigationItem] representing the navigation items.
 * @param selected Index of the currently selected item.
 * @param onItemClick Callback function invoked when a navigation item is clicked.
 */
@Composable
fun NewsBottomNavigation(
    items: List<BottomNavigationItem>,
    selected: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 0.dp
    ) {
        items.forEachIndexed { index, bottomNavigationItem ->
            NavigationBarItem(
                selected = index == selected,
                onClick = { onItemClick(index) },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            modifier = Modifier.size(IconSize),
                            painter = painterResource(id = bottomNavigationItem.icon),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.height(ExtraSmallPadding2))
                        Text(
                            text = bottomNavigationItem.text,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(id = R.color.body),
                    unselectedTextColor = colorResource(id = R.color.body),
                    indicatorColor = MaterialTheme.colorScheme.background
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun NewsBottomNavigationPreview() {
    NewsAppTheme {
        Surface {
            NewsBottomNavigation(
                items = listOf(
                    BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
                    BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
                    BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark")
                ),
                selected = 1,
                onItemClick = {}
            )
        }
    }
}