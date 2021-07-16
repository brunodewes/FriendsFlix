package com.friendsflix.presentation.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.friendsflix.databinding.ActivitySignupBinding
import com.friendsflix.presentation.home.HomeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : AppCompatActivity() {

    private val viewModel by viewModel<SignUpViewModel>()
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupListeners()
    }

    private fun setupViewModel() {
        viewModel.state.observe(this) { state ->
            when (state) {
                SignUpState.NavigateToHome -> navigateToHome()
                is SignUpState.Loading -> showLoading(state.loading)
                is SignUpState.Error -> showError(state.message)
            }
        }
    }

    private fun setupListeners() = binding.run {
        confirmSignupButton.setOnClickListener {
            viewModel.signUp(
                username = signupUsernameEt.text.toString(),
                password = signupPasswordEt.text.toString()
            )
        }
    }

    private fun navigateToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    private fun showLoading(loading: Boolean) {

    }

    private fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}