package com.example.app.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.app.R
import com.example.presentation.viewmodels.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.loginResult.observe(this, { result ->
            // Update UI based on login result
        })

        findViewById<Button>(R.id.login_button).setOnClickListener {
            // Trigger login process
            viewModel.login(username = "username", password = "password")
        }
    }
}