package com.example.data.datasources

import com.example.domain.entities.LoginResult
import com.example.domain.entities.SignupResult
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApi {
    @POST("/login")
    suspend fun login(@Query("username") username: String, @Query("password") password: String): Response<LoginResult>

    @POST("/signup")
    suspend fun signup(@Query("username") username: String, @Query("password") password: String, @Query("email") email: String): Response<SignupResult>
}

class RemoteDataSource(retrofit: Retrofit) {

    private val userApi = retrofit.create(UserApi::class.java)

    suspend fun login(username: String, password: String): Result<LoginResult> {
        return try {
            val response = userApi.login(username, password)
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(RuntimeException("Error logging in"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signup(username: String, password: String, email: String): Result<SignupResult> {
        return try {
            val response = userApi.signup(username, password, email)
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(RuntimeException("Error signing up"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}