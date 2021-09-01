package com.friendsflix.data.remote.service

import com.friendsflix.data.remote.model.LoginResponse
import com.friendsflix.data.remote.model.MovieCategoryResponse
import com.friendsflix.data.remote.model.MovieDetailResponse
import com.friendsflix.presentation.profile.Profile
import retrofit2.http.*

interface MovieService {
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("signup")
    suspend fun signup(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("name") name: String,
        @Field("age") age: Int
    ): LoginResponse

    @POST("movie_list")
    @FormUrlEncoded
    suspend fun movieList(@Field("category") category: String): MovieCategoryResponse

    @POST("movie_detail")
    @FormUrlEncoded
    suspend fun movieDetail(
        @Field("movie_id") movieId: Int,
        @Field("user_id") userId: Int
    ): MovieDetailResponse

    @POST("make_comment")
    @FormUrlEncoded
    suspend fun makeComment(
        @Field("user_id") userId: Int,
        @Field("movie_id") movieId: Int,
        @Field("comment") comment: String,
        @Field("date") date: String
    )

    @POST("favorite")
    @FormUrlEncoded
    suspend fun favorite(
        @Field("user_id") userId: Int,
        @Field("movie_id") movieId: Int,
        @Field("favorite") favorite: Boolean
    )

    @POST("rating")
    @FormUrlEncoded
    suspend fun rating(
        @Field("movie_id") movieId: Int,
        @Field("rating") rating: Float
    )

    @POST("profile")
    @FormUrlEncoded
    suspend fun profile(
        @Field("user_id") userId: Int
    ): Profile
}