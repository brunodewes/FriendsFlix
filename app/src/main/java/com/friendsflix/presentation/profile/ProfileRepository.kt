package com.friendsflix.presentation.profile

import com.friendsflix.data.remote.datasource.MovieDataSource

class ProfileRepository(
    private val dataSource: MovieDataSource
) {
    suspend fun getProfile(): Profile = TODO()
}