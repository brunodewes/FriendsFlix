package com.friendsflix.domain.model

import com.friendsflix.domain.enums.MovieCategory

data class Movie(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val category: MovieCategory
)