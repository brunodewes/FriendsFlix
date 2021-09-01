package com.friendsflix.presentation.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.friendsflix.domain.repository.MovieDetailRepository
import com.friendsflix.utils.extentions.launchSuspendFun
import java.text.SimpleDateFormat
import java.util.*

class MovieDetailViewModel(
    private val movieDetailRepository: MovieDetailRepository
) : ViewModel() {

    private val _state = MutableLiveData<MovieDetailState>()
    val state: LiveData<MovieDetailState> = _state

    fun makeComment(comment: String, movieId: Int) {
        val formato = SimpleDateFormat("dd/MM/yyyy")

        viewModelScope.launchSuspendFun(
            block = {
                movieDetailRepository.makeComment(
                    comment = comment,
                    movieId = movieId,
                    date = formato.format(Date())
                )
            },
            onSuccess = { _state.value = MovieDetailState.Reload },
            onLoading = { _state.value = MovieDetailState.Loading(it) },
            onError = { _state.value = MovieDetailState.ShowError(it.message) }
        )
    }

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launchSuspendFun(
            block = { movieDetailRepository.getMovieDetail(movieId) },
            onLoading = { _state.value = MovieDetailState.Loading(it) },
            onSuccess = { _state.value = MovieDetailState.ShowMovieDetail(it) },
            onError = { _state.value = MovieDetailState.ShowError(it.message) }
        )
    }

    fun favoriteMovie(movieId: Int, favorite: Boolean) {
        viewModelScope.launchSuspendFun(
            block = { movieDetailRepository.favoriteMovie(movieId, favorite) },
            onSuccess = { _state.value = MovieDetailState.Reload },
            onLoading = { _state.value = MovieDetailState.Loading(it) },
            onError = { _state.value = MovieDetailState.ShowError(it.message) }
        )
    }

    fun rate(movieId: Int, rate: Float) {
        viewModelScope.launchSuspendFun(
            block = { movieDetailRepository.rateMovie(movieId, rate) },
            onSuccess = { _state.value = MovieDetailState.Reload },
            onLoading = { _state.value = MovieDetailState.Loading(it) },
            onError = { _state.value = MovieDetailState.ShowError(it.message) }
        )
    }
}