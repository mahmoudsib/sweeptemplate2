package com.example.app.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.app.R
import com.example.presentation.viewmodels.SignupViewModel

class SignupActivity : AppCompatActivity() {

    private lateinit var viewModel: SignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)

        viewModel.signupResult.observe(this, { result ->
            // Update UI based on signup result
        })

        findViewById<Button>(R.id.signup_button).setOnClickListener {
            // Trigger signup process
            val username = findViewById<EditText>(R.id.username_field).text.toString()
            val password = findViewById<EditText>(R.id.password_field).text.toString()
            val email = findViewById<EditText>(R.id.email_field).text.toString()
            viewModel.signup(username = username, password = password, email = email)
        }
    }
}