package com.friendsflix.presentation.moviedetail

import com.friendsflix.domain.model.MovieDetail

sealed class MovieDetailCommentItem {
    object Title : MovieDetailCommentItem()
    data class Comment(val movieDetail: MovieDetail) : MovieDetailCommentItem()
}