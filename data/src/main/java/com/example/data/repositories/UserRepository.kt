package com.example.data.repositories

import com.example.data.datasources.LocalDataSource
import com.example.data.datasources.RemoteDataSource
import com.example.domain.entities.LoginResult
import com.example.domain.entities.SignupResult
import com.example.domain.repositories.UserRepository

interface UserRepository {
    suspend fun login(username: String, password: String): Result<LoginResult>
    suspend fun signup(username: String, password: String, email: String): Result<SignupResult>
}

class UserRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : UserRepository {

    override suspend fun login(username: String, password: String): Result<LoginResult> {
        // Try to login using the remote data source
        val remoteResult = remoteDataSource.login(username, password)
        if (remoteResult.isSuccess) {
            // If the login is successful, save the user data to the local data source
            localDataSource.login(username, password)
        }
        return remoteResult
    }

    override suspend fun signup(username: String, password: String, email: String): Result<SignupResult> {
        // Try to signup using the remote data source
        val remoteResult = remoteDataSource.signup(username, password, email)
        if (remoteResult.isSuccess) {
            // If the signup is successful, save the user data to the local data source
            localDataSource.signup(username, password, email)
        }
        return remoteResult
    }
}