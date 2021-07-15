package com.friendsflix.data.repository

import com.friendsflix.data.remote.datasource.MovieDataSource
import com.friendsflix.data.remote.mapper.toMovie
import com.friendsflix.domain.enums.MovieCategory
import com.friendsflix.domain.model.Movie
import com.friendsflix.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val movieDataSource: MovieDataSource
): HomeRepository {

    override suspend fun fetchTopRatedMovies(): List<Movie> {
        return movieDataSource.getTopRatedMovies().map { it.toMovie(MovieCategory.TOP_RATED) }
    }
}