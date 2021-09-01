package com.friendsflix.data.repository

import com.friendsflix.data.remote.datasource.MovieDataSource
import com.friendsflix.data.remote.model.MovieCategoryResponse
import com.friendsflix.domain.enums.MovieCategory
import com.friendsflix.domain.model.Movie
import com.friendsflix.domain.repository.HomeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class HomeRepositoryImpl(
    private val movieDataSource: MovieDataSource
) : HomeRepository {
    override suspend fun getAllMovies(): List<Movie> {
        val async = CoroutineScope(Dispatchers.IO).async {
            val new = async { getNew() }
            val topRated = async { getTopRated() }
            val popular = async { getPopular() }
            val action = async { getAction() }
            val upcoming = async { getUpcoming() }
            new.await().plus(topRated.await()).plus(popular.await()).plus(action.await())
                .plus(upcoming.await())
        }
        return async.await()
    }

    private suspend fun getNew(): List<Movie> {
        val category = MovieCategory.NEW
        return movieDataSource.movieList(category.title).toMovie(category)
    }

    private suspend fun getTopRated(): List<Movie> {
        val category = MovieCategory.TOP_RATED
        return movieDataSource.movieList(category.title).toMovie(category)
    }

    private suspend fun getPopular(): List<Movie> {
        val category = MovieCategory.POPULAR
        return movieDataSource.movieList(category.title).toMovie(category)
    }

    private suspend fun getAction(): List<Movie> {
        val category = MovieCategory.ACTION
        return movieDataSource.movieList(category.title).toMovie(category)
    }

    private suspend fun getUpcoming(): List<Movie> {
        val category = MovieCategory.UPCOMING
        return movieDataSource.movieList(category.title).toMovie(category)
    }

    private fun MovieCategoryResponse.toMovie(category: MovieCategory): List<Movie> {
        return this.movies.map {
            Movie(
                id = it.id,
                title = it.title,
                imageUrl = it.imageUrl,
                category = category
            )
        }
    }
}