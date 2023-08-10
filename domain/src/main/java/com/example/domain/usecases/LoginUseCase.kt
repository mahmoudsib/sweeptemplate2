package com.example.domain.usecases

import com.example.domain.entities.LoginResult
import com.example.domain.repositories.UserRepository

class LoginUseCase(private val userRepository: UserRepository) {

    fun execute(username: String, password: String): Result<LoginResult> {
        // Validate the input
        if (username.isBlank() || password.isBlank()) {
            return Result.failure(IllegalArgumentException("Username and password must not be blank"))
        }

        // Perform the login operation
        return try {
            userRepository.login(username, password)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}