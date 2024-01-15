package com.example.newsapp.presentation.details

import com.example.newsapp.domain.models.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()
    data object RemoveSideEffect : DetailsEvent()

}