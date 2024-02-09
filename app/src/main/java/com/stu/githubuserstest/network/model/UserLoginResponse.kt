package com.stu.githubuserstest.network.model

import com.google.gson.annotations.SerializedName

data class UserLoginResponse(
    @SerializedName("login") val login: String,
)
