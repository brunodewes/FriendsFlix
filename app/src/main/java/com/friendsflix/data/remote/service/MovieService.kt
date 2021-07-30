package com.friendsflix.data.remote.service

import com.friendsflix.data.remote.model.MovieCategoryResponse
import com.friendsflix.data.remote.model.MovieDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): MovieCategoryResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Int): MovieDetailResponse
}