package com.friendsflix.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.friendsflix.data.database.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(user: User): Long

    @Query("SELECT id FROM user WHERE :username = username AND :password = password")
    suspend fun login(username: String, password: String): Long
}