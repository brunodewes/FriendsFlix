package com.friendsflix.presentation.home

import com.friendsflix.domain.model.Movie

sealed class HomeState {
    data class Movies(val movies: List<Movie>): HomeState()
    data class Loading(val loading: Boolean): HomeState()
    data class Error(val message: String?): HomeState()
}