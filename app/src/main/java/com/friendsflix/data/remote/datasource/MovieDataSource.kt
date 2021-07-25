package com.friendsflix.data.remote.datasource

import com.friendsflix.data.remote.model.MovieDetailResponse
import com.friendsflix.data.remote.model.MovieResponse

interface MovieDataSource {
    suspend fun getTopRatedMovies(): List<MovieResponse>
    suspend fun getMovieDetail(movieId: Int): MovieDetailResponse
    suspend fun favoriteMovie(movieId: Int, favorite: Boolean): Boolean
    suspend fun rateMovie(movieId: Int, rating: Float): Float
}