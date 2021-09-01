package com.friendsflix.presentation.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.friendsflix.utils.extentions.launchSuspendFun

sealed class ProfileState {
    data class ShowProfile(val profile: Profile) : ProfileState()
}

class ProfileViewModel(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    val state = MutableLiveData<ProfileState>()

    fun getProfile() {
        viewModelScope.launchSuspendFun(
            block = { profileRepository.getProfile() },
            onSuccess = { state.value = ProfileState.ShowProfile(it) }
        )
    }
}