package com.friendsflix.data.repository

import com.friendsflix.data.remote.datasource.MovieDataSource
import com.friendsflix.domain.repository.SignUpRepository

class SignUpRepositoryImpl(
    private val dataSource: MovieDataSource
) : SignUpRepository {
    override suspend fun signUp(username: String, password: String, name: String, age: Int) {
        dataSource.signup(username, password, name, age)
    }
}