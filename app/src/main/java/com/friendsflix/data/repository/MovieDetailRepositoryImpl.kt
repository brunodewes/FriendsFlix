package com.friendsflix.data.repository

import com.friendsflix.data.remote.datasource.MovieDataSource
import com.friendsflix.data.remote.mapper.toMovieDetail
import com.friendsflix.domain.model.MovieDetail
import com.friendsflix.domain.repository.MovieDetailRepository

class MovieDetailRepositoryImpl(
    private val dataSource: MovieDataSource
) : MovieDetailRepository {
    override suspend fun favoriteMovie(movieId: Int, favorite: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieDetail(movieId: Int): MovieDetail {
        return dataSource.getMovieDetail(movieId).run { toMovieDetail() }
    }

    override suspend fun rateMovie(movieId: Int, rating: Float): Float {
        TODO("Not yer implemented")
    }
}