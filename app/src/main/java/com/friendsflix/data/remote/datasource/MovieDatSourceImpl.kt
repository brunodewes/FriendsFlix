package com.friendsflix.data.remote.datasource

import com.friendsflix.data.remote.model.MovieResponse
import com.friendsflix.data.remote.service.MovieService
import com.friendsflix.utils.extentions.service
import retrofit2.Retrofit

class MovieDataSourceImpl(
    retrofit: Retrofit.Builder
) : MovieDataSource {

    private val service by service<MovieService>(retrofit)

    override suspend fun getTopRatedMovies(): List<MovieResponse> =
        service.getTopRatedMovies().results
}