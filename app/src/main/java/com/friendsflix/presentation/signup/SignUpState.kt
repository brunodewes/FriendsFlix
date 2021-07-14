package com.friendsflix.presentation.signup

sealed class SignUpState {
    object NavigateToHome : SignUpState()
    data class Loading(val loading: Boolean): SignUpState()
    data class Error(val message: String?) : SignUpState()
}