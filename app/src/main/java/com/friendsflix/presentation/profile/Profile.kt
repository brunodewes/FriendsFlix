package com.friendsflix.presentation.profile

import com.friendsflix.domain.model.MovieDetailComment

data class Profile(
    val name: String,
    val age: Int,
    val comments: List<MovieDetailComment>
)