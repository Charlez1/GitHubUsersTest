package com.stu.githubuserstest.entity

data class UserListItem (
    val login: String,
    val followersCount: Int,
    val repositoryCount: Int,
    val imageUrl: String?
)