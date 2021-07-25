package com.friendsflix.domain.model

import java.util.Date

data class MovieDetailComment(
    val comment: String,
    val date: Date,
    val user: User
)