/**
 * This file is part of the NewsApp application.
 * It defines list of functions for providing dependency injection for NewsApp.
 */
package com.example.newsapp.di

import android.app.Application
import androidx.room.Room
import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.data.local.NewsDatabase
import com.example.newsapp.data.local.NewsTypeConverter
import com.example.newsapp.data.manager.LocalUserManagerImpl
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.domain.manager.LocalUserManager
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.example.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.example.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.example.newsapp.domain.usecases.news.DeleteArticle
import com.example.newsapp.domain.usecases.news.GetNews
import com.example.newsapp.domain.usecases.news.NewsUseCases
import com.example.newsapp.domain.usecases.news.SearchNews
import com.example.newsapp.domain.usecases.news.SelectArticle
import com.example.newsapp.domain.usecases.news.SelectArticles
import com.example.newsapp.domain.usecases.news.UpsertArticle
import com.example.newsapp.util.Constants.BASE_URL
import com.example.newsapp.util.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * The @Module annotation represents it as Dagger Hilt module providing dependency injections for the News App.
 *
 * The @InstallIn is an annotation in Dagger Hilt that is used to specify the Hilt component where a Dagger Hilt
 * module should be installed. It takes a single argument
 * @param SingletonComponent::class Indicates that the module's bindings should be available throughout the entire
 * application and are scoped to the application's lifecycle.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides a singleton instance of [LocalUserManager].
     *
     * @param application The application context.
     * @return A singleton instance of [LocalUserManager].
     */
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(context = application)

    /**
     * Provides a singleton instance of [AppEntryUseCases].
     *
     * @param localUserManager The local user manager.
     * @return A singleton instance of [AppEntryUseCases].
     */
    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager = localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager = localUserManager)
    )

    /**
     * Provides a singleton instance of [NewsApi].
     *
     * @return A singleton instance of [NewsApi].
     */
    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    /**
     * Provides a singleton instance of [NewsRepository].
     *
     * @param newsApi The News API service.
     * @param newsDao The DAO for local news storage.
     * @return A singleton instance of [NewsRepository].
     */
    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository =
        NewsRepositoryImpl(
            newsApi = newsApi,
            newsDao = newsDao
        )

    /**
     * Provides a singleton instance of [NewsUseCases].
     *
     * @param newsRepository The repository for news data.
     * @return A singleton instance of [NewsUseCases].
     */
    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepository: NewsRepository): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository = newsRepository),
            searchNews = SearchNews(newsRepository = newsRepository),
            upsertArticle = UpsertArticle(newsRepository = newsRepository),
            deleteArticle = DeleteArticle(newsRepository = newsRepository),
            selectArticles = SelectArticles(newsRepository = newsRepository),
            selectArticle = SelectArticle(newsRepository = newsRepository)
        )
    }

    /**
     * Provides a singleton instance of [NewsDatabase].
     *
     * @param application The application context.
     * @return A singleton instance of [NewsDatabase].
     */
    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    /**
     * Provides a singleton instance of [NewsDao].
     *
     * @param newsDatabase The database for news storage.
     * @return A singleton instance of [NewsDao].
     */
    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao

}