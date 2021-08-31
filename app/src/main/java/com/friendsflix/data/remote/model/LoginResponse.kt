package com.friendsflix.data.remote.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("user_id") val id: Int
)