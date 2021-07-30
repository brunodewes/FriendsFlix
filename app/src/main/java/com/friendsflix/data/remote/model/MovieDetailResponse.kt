package com.friendsflix.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    val id: Int,
    val title: String,
    @SerializedName("poster_path") val imageUrl: String,
    val favorite: Boolean?,
    val rating: Float?,
    val comments: List<MovieDetailCommentResponse>?
)