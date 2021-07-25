package com.friendsflix.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val id: Int,
    val name: String,
    val age: Int,
    @SerializedName("photo_url") val photoUrl: String
)