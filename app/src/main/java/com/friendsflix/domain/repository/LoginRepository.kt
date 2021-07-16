package com.friendsflix.domain.repository

interface LoginRepository {
    suspend fun login(username: String, password: String): Long
}