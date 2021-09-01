package com.friendsflix.data.repository

import com.friendsflix.data.remote.datasource.MovieDataSource
import com.friendsflix.data.remote.model.MovieDetailCommentResponse
import com.friendsflix.data.remote.model.MovieDetailResponse
import com.friendsflix.data.remote.model.UserResponse
import com.friendsflix.domain.model.MovieDetail
import com.friendsflix.domain.model.MovieDetailComment
import com.friendsflix.domain.model.User
import com.friendsflix.domain.repository.MovieDetailRepository

class MovieDetailRepositoryImpl(
    private val dataSource: MovieDataSource
) : MovieDetailRepository {
    override suspend fun favoriteMovie(movieId: Int, favorite: Boolean) {
        dataSource.favorite(movieId, favorite)
    }

    override suspend fun getMovieDetail(movieId: Int): MovieDetail {
        return dataSource.movieDetail(movieId).run { toMovieDetail() }
    }

    override suspend fun rateMovie(movieId: Int, rating: Float) {
        dataSource.rating(movieId, rating)
    }

    override suspend fun makeComment(movieId: Int, comment: String, date: String) {
        return dataSource.makeComment(movieId = movieId, comment = comment, date = date)
    }

    private fun MovieDetailResponse.toMovieDetail() = MovieDetail(
        id = id,
        title = title,
        imageUrl = imageUrl,
        favorite = favorite,
        rating = rating,
        comments = comments.map { it.toComments() }
    )

    private fun MovieDetailCommentResponse.toComments() = MovieDetailComment(
        user = user.toUser(),
        date = date,
        comment = comment
    )

    private fun UserResponse.toUser() = User(
        id = id,
        name = name,
        age = age
    )
}