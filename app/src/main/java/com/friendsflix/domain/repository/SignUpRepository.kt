package com.friendsflix.domain.repository

interface SignUpRepository {
    fun signUp(username: String, password: String): Boolean
}