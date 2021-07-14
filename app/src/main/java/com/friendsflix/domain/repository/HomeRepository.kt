package com.friendsflix.domain.repository

import com.friendsflix.domain.model.Movie

interface HomeRepository {
    suspend fun fetchPopularMovies(): List<Movie>
}