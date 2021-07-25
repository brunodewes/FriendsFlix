package com.friendsflix.presentation.moviedetail

import com.friendsflix.domain.model.MovieDetail
import com.friendsflix.domain.model.MovieDetailComment

sealed class MovieDetailState {
    data class ShowMovieDetail(val movieDetail: MovieDetail) : MovieDetailState()
    data class ShowError(val message: String?) : MovieDetailState()
    data class Loading(val loading: Boolean) : MovieDetailState()
    data class UpdateComments(val comments: List<MovieDetailComment>) : MovieDetailState()
    data class UpdateRating(val rating: Float) : MovieDetailState()
    data class UpdateFavorite(val favorite: Boolean) : MovieDetailState()
}