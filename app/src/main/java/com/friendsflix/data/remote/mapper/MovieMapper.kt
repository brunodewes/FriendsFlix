package com.friendsflix.data.remote.mapper

import com.friendsflix.data.remote.model.MovieDetailCommentResponse
import com.friendsflix.data.remote.model.MovieDetailResponse
import com.friendsflix.data.remote.model.MovieResponse
import com.friendsflix.data.remote.model.UserResponse
import com.friendsflix.domain.enums.MovieCategory
import com.friendsflix.domain.model.Movie
import com.friendsflix.domain.model.MovieDetail
import com.friendsflix.domain.model.MovieDetailComment
import com.friendsflix.domain.model.User

fun MovieResponse.toMovie(category: MovieCategory) = Movie(
    id = id,
    title = title,
    imageUrl = imageUrl,
    category = category
)

fun MovieDetailResponse.toMovieDetail() = MovieDetail(
    id = id,
    title = title,
    imageUrl = imageUrl,
    favorite = favorite ?: false,
    rating = rating ?: 0f,
    comments = comments?.map { it.toComments() }
)

fun MovieDetailCommentResponse.toComments() = MovieDetailComment(
    user = user.toUser(),
    date = date,
    comment = comment
)

fun UserResponse.toUser() = User(
    id = id,
    name = name,
    age = age,
    photoUrl = photoUrl
)