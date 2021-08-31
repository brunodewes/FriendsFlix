package com.friendsflix.data.remote.datasource

import com.friendsflix.data.remote.model.LoginResponse
import com.friendsflix.data.remote.model.MovieCategoryResponse
import com.friendsflix.data.remote.model.MovieDetailResponse
import com.friendsflix.data.remote.service.MovieService
import com.friendsflix.utils.extentions.service
import retrofit2.Retrofit

class MovieDataSourceImpl(
    retrofit: Retrofit.Builder
) : MovieDataSource {
    private val service by service<MovieService>(retrofit)

    override suspend fun login(username: String, password: String): LoginResponse {
        return service.login(username, password)
    }

    override suspend fun signup(
        username: String,
        password: String,
        name: String,
        age: Int
    ): LoginResponse {
        return service.signup(username, password, name, age)
    }

    override suspend fun movieList(category: String): MovieCategoryResponse {
        return service.movieList(category)
    }

    override suspend fun movieDetail(movieId: Int): MovieDetailResponse {
        return service.movieDetail(movieId)
    }

    override suspend fun makeComment(userId: Int, movieId: Int, comment: String, date: String) {
        service.makeComment(userId, movieId, comment, date)
    }

    override suspend fun favorite(userId: Int, movieId: Int, favorite: Boolean) {
        service.favorite(userId, movieId, favorite)
    }

    override suspend fun rating(movieId: Int, rating: Float) {
        service.rating(movieId, rating)
    }
}