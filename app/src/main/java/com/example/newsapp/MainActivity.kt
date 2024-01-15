package com.example.newsapp

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
import androidx.lifecycle.lifecycleScope
import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.models.Source
import com.example.newsapp.presentation.navgraph.NavGraph
import com.example.newsapp.ui.theme.NewsAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var newsDao: NewsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashCondition
            }
        }
        lifecycleScope.launch {
            newsDao.upsert(
                Article(
                    author = "Julian Lin",
                    content = "KE ZHUANG\r\nIs there a more beaten-down growth stock than SolarEdge (SEDG)? 2023 saw tech stocks across the board recover much of their losses suffered in 2022. SEDG appears to be experiencing a delay… [+9106 chars]",
                    description = "SolarEdge faces challenges but remains optimistic with a strong cash balance sheet and attractive valuation. Check out the full analysis of SEDG stock.",
                    publishedAt = "2024-01-01T09:15:34Z",
                    source = Source(id = "", name = "BBC"),
                    title = "SolarEdge: I Was So Wrong, But This Is Too Much",
                    url = "https://seekingalpha.com/article/4660590-solaredge-i-was-so-wrong-but-this-is-too-much",
                    urlToImage = "https://static.seekingalpha.com/cdn/s3/uploads/getty_images/1468753570/image_1468753570.jpg?io=getty-c-w1536"
                )
            )
        }
        setContent {
            NewsAppTheme {
                val isSystemDarkTheme = isSystemInDarkTheme()
                val systemUiController = rememberSystemUiController()

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemDarkTheme
                    )
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val startDestination = viewModel.startDestination
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }
}