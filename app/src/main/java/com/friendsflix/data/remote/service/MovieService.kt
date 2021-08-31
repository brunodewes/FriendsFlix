package com.friendsflix.data.remote.service

import com.friendsflix.data.remote.model.LoginResponse
import com.friendsflix.data.remote.model.MovieCategoryResponse
import com.friendsflix.data.remote.model.MovieDetailResponse
import retrofit2.http.*

interface MovieService {
    @POST("login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginResponse

    @POST("signup")
    suspend fun signup(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("name") name: String,
        @Field("age") age: Int
    ): LoginResponse

    @POST("movie_list")
    suspend fun movieList(@Field("category") category: String): MovieCategoryResponse

    @POST("movie_detail")
    suspend fun movieDetail(@Field("movie_id") movieId: Int): MovieDetailResponse

    @POST("make_comment")
    suspend fun makeComment(
        @Field("user_id") userId: Int,
        @Field("movie_id") movieId: Int,
        @Field("comment") comment: String,
        @Field("date") date: String
    )

    @POST("favorite")
    suspend fun favorite(
        @Field("user_id") userId: Int,
        @Field("movie_id") movieId: Int,
        @Field("favorite") favorite: Boolean
    )

    @POST("rating")
    suspend fun rating(
        @Field("movie_id") movieId: Int,
        @Field("rating") rating: Float
    )
}