package com.example.data.datasources

import androidx.room.Dao
import androidx.room.Query
import com.example.domain.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User WHERE username = :username AND password = :password")
    suspend fun login(username: String, password: String): User?

    @Query("INSERT INTO User (username, password, email) VALUES (:username, :password, :email)")
    suspend fun signup(username: String, password: String, email: String): Long
}

class LocalDataSource(private val userDao: UserDao) {

    suspend fun login(username: String, password: String): Result<User> {
        return try {
            val user = userDao.login(username, password)
            if (user != null) {
                Result.success(user)
            } else {
                Result.failure(RuntimeException("Invalid username or password"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signup(username: String, password: String, email: String): Result<Long> {
        return try {
            val userId = userDao.signup(username, password, email)
            Result.success(userId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}