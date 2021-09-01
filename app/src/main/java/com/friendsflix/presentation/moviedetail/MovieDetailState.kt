package com.friendsflix.presentation.moviedetail

import com.friendsflix.domain.model.MovieDetail
import com.friendsflix.domain.model.MovieDetailComment

sealed class MovieDetailState {
    data class ShowMovieDetail(val movieDetail: MovieDetail) : MovieDetailState()
    data class ShowError(val message: String?) : MovieDetailState()
    data class Loading(val loading: Boolean) : MovieDetailState()
    object Reload : MovieDetailState()
}