/*
 * This file is part of the NewsApp application.
 * It defines the HomeScreen composable, responsible for rendering the Home screen UI.
 */
package com.example.newsapp.presentation.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.domain.models.Article
import com.example.newsapp.presentation.Dimens.MediumPadding1
import com.example.newsapp.presentation.common.ArticlesList
import com.example.newsapp.presentation.common.SearchBar
import kotlinx.coroutines.delay

/**
 * Composable function representing the Home screen UI in the NewsApp application.
 *
 * @param state The current state of the Home screen.
 * @param event Function to handle events triggered by user interactions on the Home screen.
 * @param articles LazyPagingItems representing the list of news articles to be displayed.
 * @param navigateToSearch Function to navigate to the search screen.
 * @param navigateToDetails Function to navigate to the details screen for a specific article.
 */
@Composable
fun HomeScreen(
    state: HomeState,
    event: (HomeEvent) -> Unit,
    articles: LazyPagingItems<Article>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit
) {
    // Calculate titles for horizontal scrolling based on the first 10 articles
    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items.slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83D\uDD34 ") { it.title }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {
        Image(
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = MediumPadding1),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        SearchBar(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = {
                navigateToSearch()
            },
            onSearch = {}
        )
        Spacer(modifier = Modifier.height(MediumPadding1))

        // Horizontal scrolling text with article titles
        val scrollState = rememberScrollState()
        LaunchedEffect(key1 = scrollState.maxValue) {
            delay(500)
            if (scrollState.maxValue > 0) {
                // Animate scrolling to the maximum value with infinite repetition
                scrollState.animateScrollTo(
                    value = scrollState.maxValue,
                    animationSpec = infiniteRepeatable(
                        // Tween animation with specific duration, easing, and delay
                        tween(
                            durationMillis = (scrollState.maxValue - scrollState.value) * 80_000 / scrollState.maxValue,
                            easing = LinearEasing,
                            delayMillis = 2000
                        )
                    )
                )
            }
        }
        // LaunchedEffect to handle the maximum scroll value change event
        LaunchedEffect(key1 = state.maxScrollValue) {
            // Trigger the HomeEvent to update the maximum scroll value
            event(HomeEvent.UpdateMaxScrollValue(state.maxScrollValue))
        }
        // LaunchedEffect to handle the current scroll value change event
        LaunchedEffect(key1 = state.scrollValue) {
            // Trigger the HomeEvent to update the current scroll value
            event(HomeEvent.UpdateScrollValue(state.scrollValue))
        }
        /*
         * The Text composable displays horizontally scrolling titles.
         * It uses the horizontalScroll modifier with the scrollState to enable horizontal scrolling.
         */
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MediumPadding1)
                .horizontalScroll(
                    state = scrollState,
                    enabled = false
                ),
            text = titles,
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )

        Spacer(modifier = Modifier.height(MediumPadding1))
        ArticlesList(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            articles = articles,
            onClick = { article ->
                navigateToDetails(article)
            }
        )
    }
}