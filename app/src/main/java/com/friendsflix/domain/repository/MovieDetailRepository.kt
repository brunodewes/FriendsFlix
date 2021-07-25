package com.friendsflix.domain.repository

import com.friendsflix.domain.model.MovieDetail

interface MovieDetailRepository {
    suspend fun getMovieDetail(movieId: Int): MovieDetail
    suspend fun favoriteMovie(movieId: Int, favorite: Boolean): Boolean
    suspend fun rateMovie(movieId: Int, rating: Float): Float
}