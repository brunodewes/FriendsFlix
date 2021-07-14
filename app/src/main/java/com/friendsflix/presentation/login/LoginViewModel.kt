package com.friendsflix.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.friendsflix.domain.repository.LoginRepository
import com.friendsflix.utils.extentions.launchSuspendFun

class LoginViewModel(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _state = MutableLiveData<LoginState>()
    val state: LiveData<LoginState> = _state

    fun login(username: String, password: String) {
        viewModelScope.launchSuspendFun(
            block = {
                loginRepository.login(
                    username = username,
                    password = password
                )
            },
            onSuccess = { _state.value = LoginState.NavigateToHome },
            onLoading = { _state.value = LoginState.Loading(it) },
            onError = { _state.value = LoginState.Error(it.message) }
        )
    }
}