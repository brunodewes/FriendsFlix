package com.friendsflix.data.remote.service

import com.friendsflix.data.remote.model.MovieCategoryResponse
import retrofit2.http.GET

interface MovieService {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): MovieCategoryResponse
}