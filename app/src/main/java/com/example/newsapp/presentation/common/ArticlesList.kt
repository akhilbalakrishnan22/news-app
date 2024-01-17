/*
 * This file is part of the NewsApp application.
 * It defines composable functions for rendering a list of articles.
 */
package com.example.newsapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.domain.models.Article
import com.example.newsapp.presentation.Dimens.ExtraSmallPadding2
import com.example.newsapp.presentation.Dimens.MediumPadding1

/**
 * Composable function for rendering a list of articles using a LazyColumn.
 *
 * @param modifier The modifier for styling or layout customization.
 * @param articles The list of articles to be displayed.
 * @param onClick The callback function invoked when an article is clicked.
 */
@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onClick: (Article) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        contentPadding = PaddingValues(all = ExtraSmallPadding2)
    ) {
        items(count = articles.size) { index ->
            val article = articles[index]
            ArticleCard(
                article = article,
                onClick = { onClick(article) }
            )
        }
    }
}


/**
 * Composable function for rendering a list of articles using a LazyColumn with LazyPagingItems.
 * It handles loading and error states, and displays a shimmer effect for loading and an empty screen for errors.
 *
 * @param modifier The modifier for styling or layout customization.
 * @param articles The LazyPagingItems representing the paginated list of articles.
 * @param onClick The callback function invoked when an article is clicked.
 */
@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit
) {
    // Handling paging result, including shimmer effect for loading and empty screen for errors
    val handlePagingResult = handlePagingResult(articles = articles)
    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {
            items(count = articles.itemCount) { index ->
                articles[index]?.let { article ->
                    ArticleCard(
                        article = article,
                        onClick = { onClick(article) }
                    )
                }
            }
        }
    }
}

/**
 * Function to handle the paging result, including shimmer effect for loading and empty screen for errors.
 *
 * @param articles The LazyPagingItems representing the paginated list of articles.
 * @return True if the paging result is handled and articles can be displayed, false otherwise.
 */
@Composable
fun handlePagingResult(articles: LazyPagingItems<Article>): Boolean {
    // Extracting the load state and error, if any
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    // Handling different states and displaying shimmer or empty screen accordingly
    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen()
            false
        }

        else -> {
            true
        }
    }
}

/**
 * Composable function for displaying a shimmer effect indicating loading.
 */
@Composable
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(MediumPadding1)) {
        // Displaying multiple shimmer effects to simulate loading for each article
        repeat(10) {
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = MediumPadding1)
            )
        }
    }
}