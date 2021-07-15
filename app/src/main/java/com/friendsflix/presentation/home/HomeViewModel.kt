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

    init {
        getPopularMovies()
    }

    private val _state = MutableLiveData<HomeState>()
    val state: LiveData<HomeState> = _state

    private fun getPopularMovies() {
        viewModelScope.launchSuspendFun(
            block = { homeRepository.fetchTopRatedMovies() },
            onSuccess = { _state.value = HomeState.Movies(it) },
            onLoading = { _state.value = HomeState.Loading(it) },
            onError = { _state.value = HomeState.Error(it.message) }
        )
    }
}