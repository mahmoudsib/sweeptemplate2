package com.example.domain.usecases

import com.example.domain.entities.SignupResult
import com.example.domain.repositories.UserRepository

class SignupUseCase(private val userRepository: UserRepository) {

    fun execute(username: String, password: String, email: String): Result<SignupResult> {
        // Validate the input
        if (username.isBlank() || password.isBlank() || email.isBlank()) {
            return Result.failure(IllegalArgumentException("Username, password, and email must not be blank"))
        }

        // Perform the signup operation
        return userRepository.signup(username, password, email)
    }
}