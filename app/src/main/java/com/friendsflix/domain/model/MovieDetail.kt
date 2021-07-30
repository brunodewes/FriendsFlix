package com.friendsflix.domain.model

class MovieDetail(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val favorite: Boolean,
    val rating: Float,
    val comments: List<MovieDetailComment>?
)