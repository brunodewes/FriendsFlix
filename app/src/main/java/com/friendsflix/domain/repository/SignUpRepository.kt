package com.friendsflix.domain.repository

interface SignUpRepository {
    suspend fun signUp(username: String, password: String): Boolean
}