package com.friendsflix.data.remote.model

import java.util.*

class MovieDetailCommentResponse(
    val comment: String,
    val date: String,
    val user: UserResponse
)