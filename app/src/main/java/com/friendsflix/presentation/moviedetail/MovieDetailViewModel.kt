package com.friendsflix.presentation.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.friendsflix.domain.repository.MovieDetailRepository
import com.friendsflix.utils.extentions.launchSuspendFun

class MovieDetailViewModel(
    private val movieDetailRepository: MovieDetailRepository
) : ViewModel() {

    private val _state = MutableLiveData<MovieDetailState>()
    val state: LiveData<MovieDetailState> = _state

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launchSuspendFun(
            block = { movieDetailRepository.getMovieDetail(movieId) },
            onLoading = { _state.value = MovieDetailState.Loading(it) },
            onSuccess = { _state.value = MovieDetailState.ShowMovieDetail(it) },
            onError = { _state.value = MovieDetailState.ShowError(it.message) }
        )
    }
}