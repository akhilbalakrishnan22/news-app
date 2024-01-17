/*
 * This file is part of the NewsApp application.
 * It defines the DetailsScreen composable, responsible for rendering the Details screen displaying article details.
 */
package com.example.newsapp.presentation.details

import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.R
import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.models.Source
import com.example.newsapp.presentation.Dimens.ArticleImageHeight
import com.example.newsapp.presentation.Dimens.MediumPadding1
import com.example.newsapp.presentation.details.components.DetailsTopBar
import com.example.newsapp.ui.theme.NewsAppTheme

/**
 * Composable function representing the Details screen displaying article details.
 *
 * @param article The article object containing details to be displayed.
 * @param event Callback function to handle Details events.
 * @param navigateUp Callback function to navigate back to the previous screen.
 */
@Composable
fun DetailsScreen(
    article: Article,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        // Top app bar for the Details screen, with actions for browsing, sharing, bookmarking, and back navigation
        DetailsTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBookmarkClick = {
                event(DetailsEvent.UpsertDeleteArticle(article = article))
            },
            onBackClick = navigateUp
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = MediumPadding1,
                top = MediumPadding1,
                end = MediumPadding1
            )
        ) {
            item {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium)
                        .background(colorResource(id = R.color.shimmer)),
                    model = ImageRequest.Builder(context = context).data(data = article.urlToImage)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(MediumPadding1))
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(id = R.color.text_title)
                )
                Text(
                    text = article.content,
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = R.color.body)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailsScreenPreview() {
    NewsAppTheme {
        Surface {
            DetailsScreen(
                article = Article(
                    author = "Julian Lin",
                    content = "KE ZHUANG\r\nIs there a more beaten-down growth stock than SolarEdge (SEDG)? 2023 saw tech stocks across the board recover much of their losses suffered in 2022. SEDG appears to be experiencing a delay… [+9106 chars]",
                    description = "SolarEdge faces challenges but remains optimistic with a strong cash balance sheet and attractive valuation. Check out the full analysis of SEDG stock.",
                    publishedAt = "2024-01-01T09:15:34Z",
                    source = Source(id = "", name = "BBC"),
                    title = "SolarEdge: I Was So Wrong, But This Is Too Much",
                    url = "https://seekingalpha.com/article/4660590-solaredge-i-was-so-wrong-but-this-is-too-much",
                    urlToImage = "https://static.seekingalpha.com/cdn/s3/uploads/getty_images/1468753570/image_1468753570.jpg?io=getty-c-w1536"
                ),
                event = {},
                navigateUp = {}
            )
        }
    }
}