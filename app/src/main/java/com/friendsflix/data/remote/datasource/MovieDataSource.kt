package com.friendsflix.data.remote.datasource

import com.friendsflix.data.remote.model.MovieResponse

interface MovieDataSource {
    suspend fun getTopRatedMovies(): List<MovieResponse>
}