package com.friendsflix.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val id: Int,
    val title: String,
    @SerializedName("image_url") val imageUrl: String
)