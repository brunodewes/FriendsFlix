package com.friendsflix.data.repository

import com.friendsflix.data.database.dao.UserDao
import com.friendsflix.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val userDao: UserDao
) : LoginRepository {
    override suspend fun login(username: String, password: String): Long {
        return userDao.login(username, password)
    }
}