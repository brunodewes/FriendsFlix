package com.friendsflix.data.repository

import com.friendsflix.data.database.dao.UserDao
import com.friendsflix.data.database.model.User
import com.friendsflix.domain.repository.SignUpRepository

class SignUpRepositoryImpl(
    private val userDao: UserDao
) : SignUpRepository {
    override suspend fun signUp(username: String, password: String): Long {
        return userDao.insert(
            User(
                username = username,
                password = password
            )
        )
    }
}