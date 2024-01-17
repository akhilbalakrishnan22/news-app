/*
 * This file is part of the NewsApp application.
 * It defines a collection of use cases related to news operations in the NewsApp domain.
 */
package com.example.newsapp.domain.usecases.news

/**
 * Data class representing a collection of use cases for news operations in the NewsApp domain.
 *
 * @property getNews The use case for retrieving a flow of paged news articles.
 * @property searchNews The use case for searching news articles based on a query.
 * @property upsertArticle The use case for updating or inserting a news article.
 * @property deleteArticle The use case for deleting a news article.
 * @property selectArticles The use case for selecting a list of news articles.
 * @property selectArticle The use case for selecting a single news article by its URL.
 */
data class NewsUseCases(
    val getNews: GetNews,
    val searchNews: SearchNews,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val selectArticles: SelectArticles,
    val selectArticle: SelectArticle
)
