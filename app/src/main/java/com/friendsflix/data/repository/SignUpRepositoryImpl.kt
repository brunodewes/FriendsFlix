package com.friendsflix.data.repository

import com.friendsflix.domain.repository.SignUpRepository

class SignUpRepositoryImpl : SignUpRepository {
    override suspend fun signUp(username: String, password: String): Boolean {
        TODO("Not yet implemented")
    }
}