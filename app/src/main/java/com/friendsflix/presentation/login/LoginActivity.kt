package com.friendsflix.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.friendsflix.databinding.ActivityLoginBinding
import com.friendsflix.presentation.home.HomeActivity
import com.friendsflix.presentation.signup.SignUpActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel by viewModel<LoginViewModel>()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupListeners()
    }

    private fun setupViewModel() {
        viewModel.state.observe(this) { state ->
            when (state) {
                LoginState.NavigateToHome -> navigateToHome()
                is LoginState.Loading -> showLoading(state.loading)
                is LoginState.Error -> showErrorMessage(state.message)
            }
        }
    }

    private fun setupListeners() = binding.run {
        confirmLoginButton.setOnClickListener {
            viewModel.login(
                username = loginUsernameEt.text.toString(),
                password = loginPasswordEt.text.toString()
            )
        }

        confirmSignupButton.setOnClickListener {
            navigateToSignup()
        }
    }

    private fun navigateToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    private fun navigateToSignup() {
        startActivity(Intent(this, SignUpActivity::class.java))
    }

    private fun showLoading(loading: Boolean) {

    }

    private fun showErrorMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}