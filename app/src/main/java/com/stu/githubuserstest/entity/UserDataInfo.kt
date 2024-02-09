package com.stu.githubuserstest.entity

data class UserDataInfo(
    val login : String,
    val name : String,
    val imageUrl : String?,
    val company : String?,
    val email : String?,
    val blog: String?,
    val location : String?,
    val bio : String?
)