package com.friendsflix.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.friendsflix.domain.repository.HomeRepository
import com.friendsflix.utils.extentions.launchSuspendFun

class HomeViewModel(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _state = MutableLiveData<HomeState>()
    val state: LiveData<HomeState> = _state

    fun getPopularMovies() {
        viewModelScope.launchSuspendFun(
            block = { homeRepository.getAllMovies() },
            onSuccess = { _state.value = HomeState.Movies(it) },
            onLoading = { _state.value = HomeState.Loading(it) },
            onError = { _state.value = HomeState.Error(it.message) }
        )
    }
}