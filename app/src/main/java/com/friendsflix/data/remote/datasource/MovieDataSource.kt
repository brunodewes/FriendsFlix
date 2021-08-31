package com.friendsflix.data.remote.datasource

import com.friendsflix.data.remote.model.LoginResponse
import com.friendsflix.data.remote.model.MovieCategoryResponse
import com.friendsflix.data.remote.model.MovieDetailResponse
import com.friendsflix.data.remote.model.MovieResponse
import retrofit2.http.Field
import retrofit2.http.POST

interface MovieDataSource {
    suspend fun login(
        username: String,
        password: String
    ): LoginResponse

    suspend fun signup(
        username: String,
        password: String,
        name: String,
        age: Int
    ): LoginResponse

    suspend fun movieList(category: String): MovieCategoryResponse

    suspend fun movieDetail(movieId: Int): MovieDetailResponse

    suspend fun makeComment(
        userId: Int,
        movieId: Int,
        comment: String,
        date: String
    )

    suspend fun favorite(
        userId: Int,
        movieId: Int,
        favorite: Boolean
    )

    suspend fun rating(
        movieId: Int,
        rating: Float
    )
}