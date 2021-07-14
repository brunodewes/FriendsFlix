package com.friendsflix.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.friendsflix.domain.repository.SignUpRepository
import com.friendsflix.utils.extentions.launchSuspendFun

class SignUpViewModel(
    private val signUpRepository: SignUpRepository
) : ViewModel() {

    private val _state = MutableLiveData<SignUpState>()
    val state: LiveData<SignUpState> = _state

    fun signUp(username: String, password: String) {
        viewModelScope.launchSuspendFun(
            block = {
                signUpRepository.signUp(
                    username = username,
                    password = password
                )
            },
            onSuccess = { _state.value = SignUpState.NavigateToHome },
            onLoading = { _state.value = SignUpState.Loading(it) },
            onError = { _state.value = SignUpState.Error(it.message) }
        )
    }
}