package com.friendsflix.data.repository

import com.friendsflix.data.remote.datasource.MovieDataSource
import com.friendsflix.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val movieDataSource: MovieDataSource
) : LoginRepository {
    override suspend fun login(username: String, password: String) {
        movieDataSource.login(username, password)
    }
}