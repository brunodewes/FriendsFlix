package com.friendsflix.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val category: MovieCategory
)