/*
 * This file is part of the NewsApp application.
 * It defines the UI components for displaying the Bookmark screen. The Bookmark screen shows a list of bookmarked articles.
 */
package com.example.newsapp.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.newsapp.R
import com.example.newsapp.domain.models.Article
import com.example.newsapp.presentation.Dimens.MediumPadding1
import com.example.newsapp.presentation.common.ArticlesList

/**
 * Composable function to display the Bookmark screen.
 *
 * @param state The state representing the Bookmark screen.
 * @param navigateToDetails Callback function to navigate to the details screen for an article.
 */
@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetails: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = MediumPadding1,
                top = MediumPadding1,
                end = MediumPadding1
            ),
    ) {
        Text(
            text = "Bookmarks",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.SemiBold),
            color = colorResource(id = R.color.text_title)
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        ArticlesList(
            articles = state.articles,
            onClick = { article ->
                navigateToDetails(article)
            }
        )
    }
}