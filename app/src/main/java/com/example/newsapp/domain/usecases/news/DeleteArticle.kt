/*
 * This file is part of the NewsApp application.
 * It defines the use case related to news operations in the NewsApp domain.
 */
package com.example.newsapp.domain.usecases.news

import com.example.newsapp.domain.models.Article
import com.example.newsapp.domain.repository.NewsRepository

/**
 * Use case responsible for deleting a news article.
 *
 * This use case delegates the operation to a [NewsRepository] to perform the deletion.
 *
 * @property newsRepository The repository responsible for managing news-related data.
 */
class DeleteArticle(
    private val newsRepository: NewsRepository
) {

    /**
     * Invokes the use case, initiating the process of deleting a news article.
     *
     * @param article The [Article] object to be deleted.
     */
    suspend operator fun invoke(article: Article) {
        newsRepository.deleteArticle(article = article)
    }
}