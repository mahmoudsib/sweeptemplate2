package com.example.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.SignupUseCase
import com.example.domain.entities.SignupResult

class SignupViewModel(private val signupUseCase: SignupUseCase) : ViewModel() {

    private val _signupResult = MutableLiveData<SignupResult>()
    val signupResult: LiveData<SignupResult> get() = _signupResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun signup(username: String, password: String, email: String) {
        val result = signupUseCase.execute(username, password, email)
        if (result.isSuccess) {
            _signupResult.value = result.getOrNull()
        } else {
            _errorMessage.value = result.exceptionOrNull()?.message
        }
    }
}