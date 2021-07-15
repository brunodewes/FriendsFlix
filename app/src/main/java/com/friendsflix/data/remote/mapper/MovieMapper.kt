package com.friendsflix.data.remote.mapper

import com.friendsflix.data.remote.model.MovieResponse
import com.friendsflix.domain.enums.MovieCategory
import com.friendsflix.domain.model.Movie

fun MovieResponse.toMovie(category: MovieCategory) = Movie(
    id = id,
    title = title,
    imageUrl = imageUrl,
    category = category
)