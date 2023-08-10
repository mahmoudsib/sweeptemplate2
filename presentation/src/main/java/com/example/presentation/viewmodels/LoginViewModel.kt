package com.example.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.LoginUseCase
import com.example.domain.entities.LoginResult

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> get() = _loginResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun login(username: String, password: String) {
        val result = loginUseCase.execute(username, password)
        if (result.isSuccess) {
            _loginResult.value = result.getOrNull()
        } else {
            _errorMessage.value = result.exceptionOrNull()?.message
        }
    }
}