package com.example.newsapp.presentation.home

sealed class HomeEvent {

    data class UpdateScrollValue(val newValue: Int) : HomeEvent()

    data class UpdateMaxScrollValue(val newValue: Int) : HomeEvent()

}