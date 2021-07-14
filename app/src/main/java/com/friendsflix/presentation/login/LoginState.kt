package com.friendsflix.presentation.login

sealed class LoginState {
    object NavigateToHome : LoginState()
    data class Error(val message: String?) : LoginState()
    data class Loading(val loading: Boolean) : LoginState()
}