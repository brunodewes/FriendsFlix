package com.friendsflix.presentation.profile

import com.friendsflix.data.remote.model.MovieCategoryResponse
import com.friendsflix.domain.model.Movie
import com.friendsflix.domain.model.MovieDetailComment

data class Profile(
    val name: String,
    val age: Int,
    val movies: MovieCategoryResponse
)