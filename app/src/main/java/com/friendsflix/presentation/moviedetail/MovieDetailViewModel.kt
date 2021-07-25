package com.friendsflix.presentation.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MovieDetailViewModel(

) : ViewModel() {

    private val _state = MutableLiveData<MovieDetailState>()
    val state: LiveData<MovieDetailState> = _state

    fun fetchMovieDetails(movieId: Int) {

    }
}