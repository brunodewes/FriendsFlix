package com.friendsflix.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.friendsflix.data.database.dao.UserDao
import com.friendsflix.data.database.model.User

@Database(
    version = 1,
    entities = [
        User::class
    ]
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}